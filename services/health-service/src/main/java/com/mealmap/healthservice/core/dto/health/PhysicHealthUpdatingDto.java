package com.mealmap.healthservice.core.dto.health;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для изменения физического здоровья")
public class PhysicHealthUpdatingDto {
    @NotNull
    @Positive
    private Integer weight;
}
