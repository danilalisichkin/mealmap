package com.mealmap.promoservice.core.dto.promo.code;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
public class PromoCodeUpdatingDto {
    @NotNull
    @Min(1)
    @Max(100)
    Integer discountPercentage;

    @NotNull
    @Future
    LocalDate endDate;

    @NotNull
    @PositiveOrZero
    Long limit;
}
