package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderHandler implements OrderLifecycleHandler {
    @Override
    public void handle(Order order) {
        // TODO: send message to notification-service(?)
        order.setStatus(OrderStatus.ON_THE_WAY);
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.DELIVERY;
    }
}
