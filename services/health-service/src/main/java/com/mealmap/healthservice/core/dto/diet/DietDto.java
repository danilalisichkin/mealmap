package com.mealmap.healthservice.core.dto.diet;

import com.mealmap.healthservice.entity.enums.DietType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о диете")
public class DietDto {
    private Long id;

    private DietType type;

    private Integer goalWeight;

    private LocalDate startDate;
}
