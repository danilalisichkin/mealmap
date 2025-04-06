package com.mealmap.orderservice.strategy.impl;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderStatusChangingHandler;
import com.mealmap.orderservice.strategy.manager.OrderLifecycleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusChangingBySupplierEmployeeHandler implements OrderStatusChangingHandler {
    private final OrderLifecycleManager lifecycleManager;

    @Override
    public void handle(Order order, OrderStatus newStatus) {
        OrderStatus oldStatus = order.getStatus();
        OrderLifecyclePhase newOrderPhase = getHandlingPhase(oldStatus, newStatus);

        lifecycleManager.processLifecyclePhaseChange(order, newOrderPhase);
    }

    @Override
    public String getSupportedRole() {
        return "SUPPLIER_EMPLOYEE";
    }
}
