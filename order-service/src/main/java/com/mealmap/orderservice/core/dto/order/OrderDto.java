package com.mealmap.orderservice.core.dto.order;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.document.value.Address;
import com.mealmap.orderservice.document.value.OrderItem;
import com.mealmap.orderservice.document.value.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {
    private ObjectId id;

    private UUID userId;

    private Address deliveryAddress;

    private OrderStatus status;

    private PaymentInfo paymentInfo;

    private LocalDateTime orderedAt;

    private LocalDateTime readyAt;

    private LocalDateTime completedAt;

    private List<OrderItemDto> items;
}
