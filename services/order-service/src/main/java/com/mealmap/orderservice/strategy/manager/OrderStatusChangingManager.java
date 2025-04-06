package com.mealmap.orderservice.strategy.manager;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderStatusChangingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderStatusChangingManager {
    private final Map<String, OrderStatusChangingHandler> handlers;

    @Autowired
    public OrderStatusChangingManager(List<OrderStatusChangingHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        OrderStatusChangingHandler::getSupportedRole,
                        handler -> handler));
    }

    public void processStatusChange(String userRole, Order order, OrderStatus newStatus) {
        handlers.get(userRole).handle(order, newStatus);
    }
}
