package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.exception.ForbiddenException;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.stereotype.Component;

import static com.mealmap.orderservice.core.message.ApplicationMessages.ORDER_NOT_PAID;

@Component
public class CompetingOrderHandler implements OrderLifecycleHandler {
    @Override
    public void handle(Order order) {
        // TODO: send message to notification-service(?)
        if (!order.getPaymentInfo().getPaymentStatus().equals(PaymentStatus.PAID)) {
            throw new ForbiddenException(ORDER_NOT_PAID);
        }

        order.setStatus(OrderStatus.COMPLETED);
    }

    @Override
    public OrderLifecyclePhase getSupportedPhase() {
        return OrderLifecyclePhase.COMPLETING;
    }
}
