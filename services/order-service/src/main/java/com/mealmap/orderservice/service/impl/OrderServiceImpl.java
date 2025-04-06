package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.core.dto.filter.OrderFilter;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.orderservice.core.mapper.OrderMapper;
import com.mealmap.orderservice.core.mapper.PageMapper;
import com.mealmap.orderservice.doc.Order;
import com.mealmap.orderservice.repository.OrderRepository;
import com.mealmap.orderservice.service.OrderPredicateService;
import com.mealmap.orderservice.service.OrderService;
import com.mealmap.orderservice.strategy.manager.OrderStatusChangingManager;
import com.mealmap.orderservice.util.PageBuilder;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.orderservice.core.message.ApplicationMessages.ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderPredicateService predicateService;

    private final OrderStatusChangingManager orderStatusChangingManager;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final PageMapper pageMapper;

    @Override
    public PageDto<OrderDto> getPageOfOrders(
            Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder,
            OrderFilter filter, String address) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Predicate predicate = predicateService.buildPredicateForOrders(filter, address);

        Page<Order> orders = predicate == null
                ? orderRepository.findAll(request)
                : orderRepository.findAll(predicate, request);

        return pageMapper.pageToPageDto(
                orderMapper.docPageToDtoPage(orders));
    }

    @Override
    public OrderDto getOrder(ObjectId id) {
        Order order = getOrderDoc(id);

        return orderMapper.docToDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(ObjectId id, OrderStatus status) {
        Order orderToUpdate = getOrderDoc(id);

        orderStatusChangingManager.processStatusChange("SUPPLIER_EMPLOYEE", orderToUpdate, status);

        return orderMapper.docToDto(
                orderRepository.save(orderToUpdate));
    }

    private Order getOrderDoc(ObjectId id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ORDER_NOT_FOUND.formatted(id.toHexString())));
    }
}
