package com.mealmap.orderservice.core.dto.price;

import com.mealmap.orderservice.core.dto.order.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class PriceCalculationRequest {
    List<OrderItemDto> orderItems;
}
