package com.mealmap.healthservice.core.dto.diet;

import com.mealmap.healthservice.entity.enums.DietType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietUpdatingDto {
    @NotNull
    private DietType type;

    @NotNull
    @Positive
    private Integer goalWeight;
}
