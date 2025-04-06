package com.mealmap.orderservice.document.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItem {
    private Long productId;

    private Long supplierId;

    private Integer priceWhenOrdered;

    private Integer quantity;
}
