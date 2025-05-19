package com.mealmap.promoservice.core.dto.promo.code;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Информация для обновления промокода")
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
