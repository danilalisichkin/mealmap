package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransferOrderForDeliveryHandler implements OrderLifecycleHandler {
    @Override
    public void handle(Order order) {
        order.setReadyAt(LocalDateTime.now());
        order.setStatus(OrderStatus.READY);
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.TRANSFER_FOR_DELIVERY;
    }
}
