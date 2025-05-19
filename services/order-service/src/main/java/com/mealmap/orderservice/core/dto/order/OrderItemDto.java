package com.mealmap.orderservice.core.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация о позиции заказа")
public class OrderItemDto {
    private Long productId;

    private Long supplierId;

    private Long priceWhenOrdered;

    private Integer quantity;
}
