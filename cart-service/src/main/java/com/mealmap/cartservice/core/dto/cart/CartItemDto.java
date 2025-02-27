package com.mealmap.cartservice.core.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartItemDto {
    private Long id;

    private Long productId;

    private Integer quantity;
}
