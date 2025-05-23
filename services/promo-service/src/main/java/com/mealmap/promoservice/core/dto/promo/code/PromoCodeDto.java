package com.mealmap.promoservice.core.dto.promo.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация о промокоде")
public class PromoCodeDto {
    private String id;

    private Long limit;

    private Integer discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;
}
