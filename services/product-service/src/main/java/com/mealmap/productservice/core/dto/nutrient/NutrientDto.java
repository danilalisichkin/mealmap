package com.mealmap.productservice.core.dto.nutrient;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о питательности")
public class NutrientDto {
    @NotNull
    private Integer calories;

    @NotNull
    @Positive
    private Integer proteins;

    @NotNull
    @Positive
    private Integer fats;

    @NotNull
    @Positive
    private Integer carbs;

    @NotNull
    @Positive
    private Integer fibers;

    @NotNull
    @Positive
    private Integer sugars;
}
