package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.doc.Order;
import com.mealmap.orderservice.strategy.OrderStatusChangingHandler;
import com.mealmap.orderservice.strategy.manager.OrderLifecycleManager;
import com.mealmap.starters.exceptionstarter.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.orderservice.core.message.ApplicationMessages.CANT_CHANGE_ORDER_STATUS;

@Component
@RequiredArgsConstructor
public class OrderStatusChangingByClientEmployeeHandler implements OrderStatusChangingHandler {
    private final OrderLifecycleManager lifecycleManager;

    @Override
    public void handle(Order order, OrderStatus newStatus) {
        OrderStatus oldStatus = order.getStatus();
        OrderLifecyclePhase newOrderPhase = getHandlingPhase(oldStatus, newStatus);

        if (newOrderPhase != OrderLifecyclePhase.CANCELLING) {
            throw new ForbiddenException(CANT_CHANGE_ORDER_STATUS.formatted(oldStatus, newStatus));
        }

        lifecycleManager.processLifecyclePhaseChange(order, newOrderPhase);
    }

    @Override
    public String getSupportedRole() {
        return "CLIENT_EMPLOYEE";
    }
}
