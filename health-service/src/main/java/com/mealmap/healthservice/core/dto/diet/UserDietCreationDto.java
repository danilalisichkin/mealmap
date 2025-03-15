package com.mealmap.healthservice.core.dto.diet;

import com.mealmap.healthservice.entity.enums.DietType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDietCreationDto {
    @NotNull
    private DietType dietType;

    @NotNull
    @Positive
    private Integer goalWeight;
}
