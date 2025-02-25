package com.mealmap.orderservice.document.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItem {
    private Long productId;

    private Long priceWhenOrdered;

    private Integer quantity;
}
