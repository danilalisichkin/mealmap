package com.mealmap.orderservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderSortField {
    ID("id"),
    USER_ID("userId"),
    DELIVERY_ADDRESS("deliveryAddress.fullAddress"),
    ORDER_STATUS("orderStatus"),
    TOTAL_AMOUNT("totalAmount"),
    DISCOUNT_AMOUNT("discountAmount"),
    PAYMENT_STATUS("paymentStatus"),
    ORDERED_AT("orderedAt"),
    READY_AT("readyAt"),
    COMPLETED_AT("completeAt");

    private final String value;
}
