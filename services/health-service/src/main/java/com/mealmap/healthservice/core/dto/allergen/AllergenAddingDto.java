package com.mealmap.healthservice.core.dto.allergen;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для добавления аллергена")
public class AllergenAddingDto {
    @NotNull
    private Long allergenId;
}
