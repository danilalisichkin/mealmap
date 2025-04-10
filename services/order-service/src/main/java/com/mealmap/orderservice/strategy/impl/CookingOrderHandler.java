package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.doc.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.stereotype.Component;

@Component
public class CookingOrderHandler implements OrderLifecycleHandler {
    @Override
    public void handle(Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.COOKING;
    }
}
