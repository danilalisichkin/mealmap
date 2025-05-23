package com.mealmap.orderservice.core.dto.order;

import com.mealmap.orderservice.document.value.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.mealmap.orderservice.core.constant.OrderLimits.MAX_ITEMS_PER_ORDER;
import static com.mealmap.orderservice.core.message.ApplicationMessages.TOO_MANY_ITEMS_IN_ORDER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для создания заказа")
public class OrderCreationDto {
    @Valid
    private Address deliveryAddress;

    @Size(min = 2, max = 20)
    private String promoCode;

    @NotEmpty
    @Size(min = 1, max = MAX_ITEMS_PER_ORDER, message = TOO_MANY_ITEMS_IN_ORDER)
    private List<@Valid OrderItemCreationDto> items;
}
