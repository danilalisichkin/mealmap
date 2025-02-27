package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.core.dto.filter.OrderFilterDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate;
import com.mealmap.orderservice.service.OrderPredicateService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.hasDeliveryAddressLike;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.hasItemWithProductId;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.hasPaymentStatus;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.hasStatus;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.isCompletedAtBetween;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.isOrderedAtBetween;
import static com.mealmap.orderservice.document.predicate.OrderQuerydslPredicate.isReadyAtBetween;

@Service
public class OrderPredicateServiceImpl implements OrderPredicateService {

    public Predicate buildPredicateForOrders(OrderFilterDto filter, String address) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();

        addFilterByUserId(predicateBuilder, filter.getUserId());
        addFilterByStatus(predicateBuilder, filter.getStatus());
        addFilterByPaymentStatus(predicateBuilder, filter.getPaymentStatus());
        addFilterByOrderedAt(predicateBuilder, filter.getOrderedAtStart(), filter.getOrderedAtEnd());
        addFilterByReadyAt(predicateBuilder, filter.getReadyAtStart(), filter.getReadyAtEnd());
        addFilterByCompletedAt(predicateBuilder, filter.getCompletedAtStart(), filter.getCompletedAtEnd());
        addFilterByProductId(predicateBuilder, filter.getProductId());
        addFilterByDeliveryAddress(predicateBuilder, address);

        return predicateBuilder.getValue();
    }

    private void addFilterByUserId(BooleanBuilder predicateBuilder, String userId) {
        if (userId != null) {
            predicateBuilder.and(OrderQuerydslPredicate.hasUserId(userId));
        }
    }

    private void addFilterByStatus(BooleanBuilder predicateBuilder, OrderStatus status) {
        if (status != null) {
            predicateBuilder.and(hasStatus(status));
        }
    }

    private void addFilterByPaymentStatus(BooleanBuilder predicateBuilder, PaymentStatus paymentStatus) {
        if (paymentStatus != null) {
            predicateBuilder.and(hasPaymentStatus(paymentStatus));
        }
    }

    private void addFilterByOrderedAt(BooleanBuilder predicateBuilder, LocalDateTime start, LocalDateTime end) {
        if (start != null || end != null) {
            predicateBuilder.and(isOrderedAtBetween(start, end));
        }
    }

    private void addFilterByReadyAt(BooleanBuilder predicateBuilder, LocalDateTime start, LocalDateTime end) {
        if (start != null || end != null) {
            predicateBuilder.and(isReadyAtBetween(start, end));
        }
    }

    private void addFilterByCompletedAt(BooleanBuilder predicateBuilder, LocalDateTime start, LocalDateTime end) {
        if (start != null || end != null) {
            predicateBuilder.and(isCompletedAtBetween(start, end));
        }
    }

    private void addFilterByProductId(BooleanBuilder predicateBuilder, Long productId) {
        if (productId != null) {
            predicateBuilder.and(hasItemWithProductId(productId));
        }
    }

    private void addFilterByDeliveryAddress(BooleanBuilder predicateBuilder, String address) {
        if (address != null && !address.isEmpty()) {
            predicateBuilder.and(hasDeliveryAddressLike(address));
        }
    }
}
