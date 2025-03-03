package com.mealmap.orderservice.client.dto.promo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoStatDto {
    private ObjectId id;

    private String promoCode;

    private String userId;

    private LocalDateTime createdAt;
}
