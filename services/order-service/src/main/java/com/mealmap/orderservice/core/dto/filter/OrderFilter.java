package com.mealmap.orderservice.core.dto.filter;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.PaymentStatus;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

import static com.mealmap.orderservice.validator.RangeValidator.isValidRange;

@Data
@Builder
@AllArgsConstructor
public class OrderFilter {
    @UUID
    private String userId;

    private OrderStatus status;

    private PaymentStatus paymentStatus;

    @PastOrPresent
    private LocalDateTime orderedAtStart;

    @PastOrPresent
    private LocalDateTime orderedAtEnd;

    @PastOrPresent
    private LocalDateTime readyAtStart;

    @PastOrPresent
    private LocalDateTime readyAtEnd;

    @PastOrPresent
    private LocalDateTime completedAtStart;

    @PastOrPresent
    private LocalDateTime completedAtEnd;

    private Long productId;

    @AssertTrue(message = "orderedAtStart must be before orderedAtEnd")
    private boolean isValidOrderedAtRange() {
        return isValidRange(orderedAtStart, orderedAtEnd);
    }

    @AssertTrue(message = "readyAtStart must be before readyAtEnd")
    private boolean isValidReadyAtRange() {
        return isValidRange(readyAtStart, readyAtEnd);
    }

    @AssertTrue(message = "completedAtStart must be before completedAtEnd")
    private boolean isValidCompletedAtRange() {
        return isValidRange(completedAtStart, completedAtEnd);
    }
}
