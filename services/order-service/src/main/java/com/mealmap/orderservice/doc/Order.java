package com.mealmap.orderservice.doc;

import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.doc.value.Address;
import com.mealmap.orderservice.doc.value.OrderItem;
import com.mealmap.orderservice.doc.value.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("orders")
public class Order {
    @Id
    private ObjectId id;

    @Indexed
    private String userId;

    private Address deliveryAddress;

    private OrderStatus status;

    private String promoCode;

    private PaymentInfo paymentInfo;

    private LocalDateTime orderedAt;

    private LocalDateTime readyAt;

    private LocalDateTime completedAt;

    private List<OrderItem> items;
}
