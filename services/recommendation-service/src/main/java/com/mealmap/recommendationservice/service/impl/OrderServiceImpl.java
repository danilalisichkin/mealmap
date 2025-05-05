package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.client.OrderApiClient;
import com.mealmap.recommendationservice.client.dto.enums.OrderSortField;
import com.mealmap.recommendationservice.core.model.order.Order;
import com.mealmap.recommendationservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderApiClient orderApiClient;

    @Value("${business.recommendation.settings.last-orders.count}")
    private int orderLimit;

    @Override
    public List<Order> getUserLastOrders(UUID userId) {
        var lastOrders = orderApiClient.getPageOfUserOrders(
                userId.toString(), 0, orderLimit, OrderSortField.ORDERED_AT, Sort.Direction.DESC);

        return lastOrders.getItems();
    }
}
