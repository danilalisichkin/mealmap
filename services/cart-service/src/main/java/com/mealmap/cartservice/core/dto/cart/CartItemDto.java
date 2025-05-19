package com.mealmap.cartservice.core.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация о позиции корзины")
public class CartItemDto {
    private Long id;

    private Long productId;

    private Integer quantity;
}
