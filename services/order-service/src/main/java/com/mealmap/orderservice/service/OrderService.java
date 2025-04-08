package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.filter.OrderFilter;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

public interface OrderService {
    PageDto<OrderDto> getPageOfOrders(
            Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder,
            OrderFilter filter, String address);

    OrderDto getOrder(ObjectId id);

    OrderDto updateOrderStatus(ObjectId id, OrderStatus status);
}
