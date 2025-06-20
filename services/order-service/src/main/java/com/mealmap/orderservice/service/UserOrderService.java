package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.order.OrderCreationDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

public interface UserOrderService {
    PageDto<OrderDto> getPageOfUserOrders(
            String userId, Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder);

    OrderDto createOrder(String userId, OrderCreationDto orderDto);

    OrderDto updateOrderStatus(String userId, ObjectId id, OrderStatus status);
}
