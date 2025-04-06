package com.mealmap.orderservice.core.dto.price;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PriceRecalculationRequest {
    String userId;
    String promoCode;
    Long basePrice;
}
