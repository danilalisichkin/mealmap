package com.mealmap.orderservice.doc.predicate;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import com.mealmap.orderservice.doc.QOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderQuerydslPredicate {
    public static BooleanExpression hasUserId(String userId) {
        return userId != null
                ? QOrder.order.userId.eq(userId)
                : null;
    }

    public static BooleanExpression hasStatus(OrderStatus status) {
        return status != null
                ? QOrder.order.status.eq(status)
                : null;
    }

    public static BooleanExpression hasPaymentStatus(PaymentStatus paymentStatus) {
        return paymentStatus != null
                ? QOrder.order.paymentInfo.paymentStatus.eq(paymentStatus)
                : null;
    }

    public static BooleanExpression isOrderedAtBetween(LocalDateTime start, LocalDateTime end) {
        return createRangePredicate(start, end, QOrder.order.orderedAt);
    }

    public static BooleanExpression isReadyAtBetween(LocalDateTime start, LocalDateTime end) {
        return createRangePredicate(start, end, QOrder.order.readyAt);
    }

    public static BooleanExpression isCompletedAtBetween(LocalDateTime start, LocalDateTime end) {
        return createRangePredicate(start, end, QOrder.order.completedAt);
    }

    public static BooleanExpression hasItemWithProductId(Long productId) {
        return productId != null
                ? QOrder.order.items.any().productId.eq(productId)
                : null;
    }

    public static BooleanExpression hasDeliveryAddressLike(String deliveryAddress) {
        return deliveryAddress != null
                ? QOrder.order.deliveryAddress.fullAddress
                .containsIgnoreCase(deliveryAddress)
                : null;
    }

    private static BooleanExpression createRangePredicate(
            LocalDateTime start, LocalDateTime end, DateTimePath<LocalDateTime> field) {

        if (start == null && end == null) {
            return null;
        }
        BooleanExpression predicate = null;
        if (start != null) {
            predicate = field.goe(start);
        }
        if (end != null) {
            predicate = predicate == null ? field.loe(end) : predicate.and(field.loe(end));
        }
        return predicate;
    }
}
