package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.filter.OrderFilter;
import com.querydsl.core.types.Predicate;

public interface OrderPredicateService {
    Predicate buildPredicateForOrders(OrderFilter filter, String address);
}
