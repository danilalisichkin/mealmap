package com.mealmap.orderservice.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderLifecyclePhase {
    ORDERING(null, OrderStatus.NEW),
    COOKING(OrderStatus.NEW, OrderStatus.IN_PROGRESS),
    TRANSFER_FOR_DELIVERY(OrderStatus.IN_PROGRESS, OrderStatus.READY),
    DELIVERY(OrderStatus.READY, OrderStatus.ON_THE_WAY),
    COMPLETING(OrderStatus.ON_THE_WAY, OrderStatus.COMPLETED),
    CANCELLING(OrderStatus.NEW, OrderStatus.CANCELED);

    private final OrderStatus oldStatus;
    private final OrderStatus currentStatus;

    public static OrderLifecyclePhase ofCurrentStatus(OrderStatus status) {
        return Arrays.stream(OrderLifecyclePhase.values())
                .filter(olp -> olp.currentStatus == status)
                .findFirst()
                .orElse(null);
    }

    public static OrderLifecyclePhase ofOldAndCurrentStatus(OrderStatus oldStatus, OrderStatus currentStatus) {
        return Arrays.stream(OrderLifecyclePhase.values())
                .filter(olp -> olp.oldStatus == oldStatus && olp.currentStatus == currentStatus)
                .findFirst()
                .orElse(null);
    }
}
