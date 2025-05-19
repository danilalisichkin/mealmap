package com.mealmap.promoservice.core.dto.promo.stat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация об использовании промокода")
public class PromoStatDto {
    private ObjectId id;

    private String promoCode;

    private String userId;

    private LocalDateTime createdAt;
}
