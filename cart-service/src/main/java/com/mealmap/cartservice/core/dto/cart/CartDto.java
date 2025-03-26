package com.mealmap.cartservice.core.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CartDto {
    private Long id;

    private UUID userId;

    private List<CartItemDto> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
