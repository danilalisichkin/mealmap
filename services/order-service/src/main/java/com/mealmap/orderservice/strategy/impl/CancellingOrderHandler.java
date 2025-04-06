package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.stereotype.Component;

@Component
public class CancellingOrderHandler implements OrderLifecycleHandler {
    @Override
    public void handle(Order order) {
        // TODO: send message to notification-service(?)
        if (order.getPaymentInfo().getPaymentStatus().equals(PaymentStatus.PAID)) {
            // TODO: refund money to account via payment-service
        }

        order.setStatus(OrderStatus.CANCELED);
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.CANCELLING;
    }
}
