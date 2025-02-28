package com.mealmap.promoservice.core.dto.promo.code;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
public class PromoCodeCreationDto {
    String value;

    Long limit;

    Integer discountPercentage;

    LocalDate startDate;

    LocalDate endDate;
}
