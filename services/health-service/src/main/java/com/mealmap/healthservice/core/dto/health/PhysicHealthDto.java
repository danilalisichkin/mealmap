package com.mealmap.healthservice.core.dto.health;

import com.mealmap.healthservice.entity.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о физическом здоровье")
public class PhysicHealthDto {
    private Long id;

    private Integer weight;

    private Integer height;

    private LocalDate birthDate;

    private Gender gender;
}
