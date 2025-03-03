package com.mealmap.orderservice.client.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutrientDto {
    private Integer calories;

    private Integer proteins;

    private Integer fats;

    private Integer carbs;

    private Integer fibers;

    private Integer sugars;
}
