package com.mealmap.orderservice.document.value;

import com.mealmap.orderservice.core.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    private Long totalAmount;

    private Long discountAmount;

    private Long paymentId;

    private PaymentStatus paymentStatus;
}
