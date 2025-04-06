package com.mealmap.promoservice.core.dto.promo.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class PromoCodeDto {
    private String value;

    private Long limit;

    private Integer discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;
}
