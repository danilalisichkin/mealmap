package com.mealmap.productservice.core.dto.nutrient;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NutrientDto {
    Integer calories;

    Integer proteins;

    Integer fats;

    Integer carbs;

    Integer fibers;

    Integer sugars;
}
