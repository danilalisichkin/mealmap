package com.mealmap.healthservice.core.dto.diet;

import com.mealmap.healthservice.entity.enums.DietType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для добавления диеты")
public class DietCreationDto {
    @NotNull
    private DietType type;

    @NotNull
    @Positive
    private Integer goalWeight;
}
