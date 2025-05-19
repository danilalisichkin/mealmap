package com.mealmap.healthservice.core.dto.allergen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация об аллергене")
public class AllergenDto {
    private Long id;

    private Long allergenId;
}
