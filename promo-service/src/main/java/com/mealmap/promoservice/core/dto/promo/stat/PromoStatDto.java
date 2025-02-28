package com.mealmap.promoservice.core.dto.promo.stat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PromoStatDto {
    private ObjectId id;
    private String promoCode;
    private String userId;
    private LocalDateTime createdAt;
}
