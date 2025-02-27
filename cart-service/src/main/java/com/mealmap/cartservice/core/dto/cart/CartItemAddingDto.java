package com.mealmap.cartservice.core.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartItemAddingDto {
    private Long productId;

    private Integer quantity;
}
