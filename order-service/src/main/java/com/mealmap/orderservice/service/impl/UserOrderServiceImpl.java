package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.core.dto.order.OrderCreationDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.orderservice.core.mapper.OrderItemMapper;
import com.mealmap.orderservice.core.mapper.OrderMapper;
import com.mealmap.orderservice.core.mapper.PageMapper;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.document.value.OrderItem;
import com.mealmap.orderservice.document.value.PaymentInfo;
import com.mealmap.orderservice.exception.ResourceNotFoundException;
import com.mealmap.orderservice.repository.OrderRepository;
import com.mealmap.orderservice.service.OrderPriceCalculationService;
import com.mealmap.orderservice.service.UserOrderService;
import com.mealmap.orderservice.strategy.manager.OrderStatusChangingManager;
import com.mealmap.orderservice.util.PageBuilder;
import com.mealmap.orderservice.validator.OrderItemValidator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.mealmap.orderservice.core.message.ApplicationMessages.USER_ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private final OrderPriceCalculationService priceCalculationService;

    private final OrderItemValidator orderItemValidator;

    private final OrderStatusChangingManager orderStatusChangingManager;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

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
    public OrderDto createOrder(String userId, OrderCreationDto orderDto) {
        orderItemValidator.validateUniquenessOfItems(orderDto.getItems());

        Order orderToCreate = initDefaultOrder(userId, orderDto);
        setItemPrices(orderToCreate.getItems());
        calculateBasePrice(orderToCreate);
        // TODO: recalculate price using promo code
        // TODO: initiate payment operation
        // TODO: send notification
        // TODO: maybe move all logic to separated handler

        return orderMapper.docToDto(
                orderRepository.save(orderToCreate));
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

    private Order initDefaultOrder(String userId, OrderCreationDto dto) {
        Order newOrder = orderMapper.dtoToDoc(dto);
        newOrder.setUserId(userId);
        newOrder.setStatus(OrderStatus.NEW);
        newOrder.setOrderedAt(LocalDateTime.now());

        return newOrder;
    }

    private void setItemPrices(List<OrderItem> items) {
        // TODO: fetch actual price from ProductService (maybe using Bulk)
        items.forEach(item -> item.setPriceWhenOrdered(100L));
    }

    private void calculateBasePrice(Order order) {
        var priceCalculationRequest = new PriceCalculationRequest(
                orderItemMapper.docListToDtoList(order.getItems()));

        long totalPrice = priceCalculationService.calculateBaseOrderPrice(priceCalculationRequest);

        order.setPaymentInfo(
                PaymentInfo.builder()
                        .totalAmount(totalPrice)
                        .build());
    }
}
