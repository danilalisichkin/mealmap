package com.mealmap.recommendationservice.client.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {
    private ObjectId id;

    private String userId;

    private LocalDateTime orderedAt;

    private List<OrderItemDto> items;
}
