package com.mealmap.productservice.core.dto.nutrient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NutrientDto {
    @NotNull
    Integer calories;

    @NotNull
    @Positive
    Integer proteins;

    @NotNull
    @Positive
    Integer fats;

    @NotNull
    @Positive
    Integer carbs;

    @NotNull
    @Positive
    Integer fibers;

    @NotNull
    @Positive
    Integer sugars;
}
