package com.mealmap.orderservice.core.dto.order;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.doc.value.Address;
import com.mealmap.orderservice.doc.value.PaymentInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация о заказе")
public class OrderDto {
    private ObjectId id;

    private String userId;

    private Address deliveryAddress;

    private OrderStatus status;

    private String promoCode;

    private PaymentInfo paymentInfo;

    private LocalDateTime orderedAt;

    private LocalDateTime readyAt;

    private LocalDateTime completedAt;

    private List<OrderItemDto> items;
}
