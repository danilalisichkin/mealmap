package com.mealmap.healthservice.core.dto.allergen;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllergenAddingDto {
    @NotNull
    private Long allergenId;
}
