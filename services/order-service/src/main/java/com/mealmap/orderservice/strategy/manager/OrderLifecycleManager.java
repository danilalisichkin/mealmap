package com.mealmap.orderservice.strategy.manager;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.strategy.OrderLifecycleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderLifecycleManager {
    private final Map<OrderLifecyclePhase, OrderLifecycleHandler> handlers;

    @Autowired
    public OrderLifecycleManager(List<OrderLifecycleHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        OrderLifecycleHandler::getSupportedPhase,
                        handler -> handler));
    }

    public void processLifecyclePhaseChange(Order order, OrderLifecyclePhase phase) {
        handlers.get(phase).handle(order);
    }
}
