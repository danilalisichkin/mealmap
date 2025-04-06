package com.mealmap.orderservice.core.dto.order;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.mealmap.orderservice.core.constant.OrderLimits.MAX_PRODUCTS_PER_ITEM;
import static com.mealmap.orderservice.core.message.ApplicationMessages.TOO_MANY_PRODUCTS_IN_ITEM;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreationDto {
    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    @Max(value = MAX_PRODUCTS_PER_ITEM, message = TOO_MANY_PRODUCTS_IN_ITEM)
    private Integer quantity;
}
