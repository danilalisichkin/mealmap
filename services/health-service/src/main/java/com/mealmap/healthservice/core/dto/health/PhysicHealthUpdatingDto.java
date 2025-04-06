package com.mealmap.healthservice.core.dto.health;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicHealthUpdatingDto {
    @NotNull
    @Positive
    private Integer weight;
}
