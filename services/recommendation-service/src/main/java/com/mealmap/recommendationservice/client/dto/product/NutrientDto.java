package com.mealmap.recommendationservice.client.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
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
