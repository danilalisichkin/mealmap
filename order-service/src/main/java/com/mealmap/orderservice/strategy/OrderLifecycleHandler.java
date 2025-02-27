package com.mealmap.orderservice.strategy;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.document.Order;

public interface OrderLifecycleHandler {
    void handle(Order order);

    OrderLifecyclePhase getSupportedPhase();
}
