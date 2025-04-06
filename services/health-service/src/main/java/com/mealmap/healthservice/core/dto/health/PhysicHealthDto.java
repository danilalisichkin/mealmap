package com.mealmap.healthservice.core.dto.health;

import com.mealmap.healthservice.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicHealthDto {
    private Long id;

    private Integer weight;

    private Integer height;

    private LocalDate birthDate;

    private Gender gender;
}
