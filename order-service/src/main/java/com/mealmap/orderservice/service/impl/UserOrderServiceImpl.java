package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.orderservice.core.mapper.OrderMapper;
import com.mealmap.orderservice.core.mapper.PageMapper;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.exception.ResourceNotFoundException;
import com.mealmap.orderservice.repository.OrderRepository;
import com.mealmap.orderservice.service.UserOrderService;
import com.mealmap.orderservice.strategy.manager.OrderStatusChangingManager;
import com.mealmap.orderservice.util.PageBuilder;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.orderservice.core.message.ApplicationMessages.USER_ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private final OrderStatusChangingManager orderStatusChangingManager;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final PageMapper pageMapper;

    @Override
    public PageDto<OrderDto> getPageOfUserOrders(
            String userId, Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Page<Order> orders = orderRepository.findAllByUserId(userId, request);

        return pageMapper.pageToPageDto(
                orderMapper.docPageToDtoPage(orders));
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(String userId, ObjectId id, OrderStatus status) {
        Order orderToUpdate = getOrderDoc(id, userId);

        orderStatusChangingManager.processStatusChange("CLIENT_EMPLOYEE", orderToUpdate, status);

        return orderMapper.docToDto(
                orderRepository.save(orderToUpdate));
    }

    private Order getOrderDoc(ObjectId id, String userId) {
        return orderRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        USER_ORDER_NOT_FOUND.formatted(id.toHexString(), userId)));
    }
}
