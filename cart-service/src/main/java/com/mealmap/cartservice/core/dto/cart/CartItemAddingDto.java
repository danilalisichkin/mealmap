package com.mealmap.cartservice.core.dto.cart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartItemAddingDto {
    @NotNull
    private Long productId;

    @NotNull
    @Positive
    @Max(20)
    private Integer quantity;
}
