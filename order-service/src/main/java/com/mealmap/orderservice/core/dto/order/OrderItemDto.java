package com.mealmap.orderservice.core.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;

    private Long priceWhenOrdered;

    private Integer quantity;
}
