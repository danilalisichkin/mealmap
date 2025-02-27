package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.filter.OrderFilterDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

public interface OrderService {
    PageDto<OrderDto> getPageOfOrders(
            Integer offset, Integer limit, OrderSortField sortBy, Sort.Direction sortOrder,
            OrderFilterDto filter, String address);

    OrderDto getOrder(ObjectId id);

    OrderDto updateOrderStatus(ObjectId id, OrderStatus status);
}
