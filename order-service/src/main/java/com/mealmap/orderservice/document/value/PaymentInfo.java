package com.mealmap.orderservice.document.value;

import com.mealmap.orderservice.core.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentInfo {
    private Long totalAmount;

    private Long discountAmount;

    private Long paymentId;

    private PaymentStatus paymentStatus;
}
