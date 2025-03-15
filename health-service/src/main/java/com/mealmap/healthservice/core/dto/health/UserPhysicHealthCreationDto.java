package com.mealmap.healthservice.core.dto.health;

import com.mealmap.healthservice.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPhysicHealthCreationDto {
    private UUID userId;

    private Integer weight;

    private Integer height;

    private LocalDate birthDate;

    private Gender gender;
}
