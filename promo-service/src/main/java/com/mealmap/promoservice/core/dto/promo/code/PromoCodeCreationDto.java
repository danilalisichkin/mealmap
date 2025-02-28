package com.mealmap.promoservice.core.dto.promo.code;

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
public class PromoCodeCreationDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    String value;

    @NotNull
    @Positive
    Long limits;

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
