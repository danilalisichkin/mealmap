package com.mealmap.promoservice.core.dto.promo.code;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
public class PromoCodeUpdatingDto {
    Integer discountPercentage;

    LocalDate endDate;

    Long limit;
}
