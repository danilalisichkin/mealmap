package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

public interface UserOrderService {
    PageDto<OrderDto> getPageOfUserOrders(
            String userId, Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder);

    OrderDto updateOrderStatus(String userId, ObjectId id, OrderStatus status);
}
