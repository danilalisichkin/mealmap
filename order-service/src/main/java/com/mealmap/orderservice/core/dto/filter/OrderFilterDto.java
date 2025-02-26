package com.mealmap.orderservice.core.dto.filter;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderFilterDto {
    private UUID userId;

    private OrderStatus status;

    private PaymentStatus paymentStatus;

    private LocalDateTime orderedAtStart;

    private LocalDateTime orderedAtEnd;

    private LocalDateTime readyAtStart;

    private LocalDateTime completedAtEnd;

    private Long productId;
}
