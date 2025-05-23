package com.mealmap.promoservice.core.dto.promo.code;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

import static com.mealmap.promoservice.validator.RangeValidator.isValidRange;

@Value
@AllArgsConstructor
@Schema(description = "Информация для создания промокода")
public class PromoCodeCreationDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    String id;

    @NotNull
    @Positive
    Long limit;

    @NotNull
    @Min(1)
    @Max(100)
    Integer discountPercentage;

    @NotNull
    @FutureOrPresent
    LocalDate startDate;

    @NotNull
    @Future
    LocalDate endDate;

    @AssertTrue(message = "startDate must be before endDate")
    private boolean isValidPriceRange() {
        return isValidRange(startDate, endDate);
    }
}
