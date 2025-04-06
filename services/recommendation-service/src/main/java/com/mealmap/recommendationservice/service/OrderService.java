package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.model.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getUserLastOrders(UUID userId);
}
