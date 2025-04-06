package com.mealmap.orderservice.strategy;

import com.mealmap.orderservice.core.enums.OrderLifecyclePhase;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.Order;
import com.mealmap.orderservice.exception.ForbiddenException;

import static com.mealmap.orderservice.core.message.ApplicationMessages.CANT_CHANGE_ORDER_STATUS;

public interface OrderStatusChangingHandler {
    void handle(Order order, OrderStatus newStatus);

    String getSupportedRole();

    default OrderLifecyclePhase getHandlingPhase(OrderStatus oldStatus, OrderStatus newStatus) {
        OrderLifecyclePhase phase = OrderLifecyclePhase.ofOldAndCurrentStatus(oldStatus, newStatus);
        if (phase == null) {
            throw new ForbiddenException(
                    CANT_CHANGE_ORDER_STATUS.formatted(oldStatus, newStatus));
        }

        return phase;
    }
}
