package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.filter.OrderFilterDto;
import com.querydsl.core.types.Predicate;

public interface OrderPredicateService {
    Predicate buildPredicateForOrders(OrderFilterDto filter, String address);
}
