package com.mealmap.healthservice.core.dto.health;

import com.mealmap.healthservice.entity.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для добавления физического здоровья")
public class PhysicHealthCreationDto {
    @NotNull
    @Positive
    private Integer weight;

    @NotNull
    @Positive
    private Integer height;

    @Past
    private LocalDate birthDate;

    @NotNull
    private Gender gender;
}
