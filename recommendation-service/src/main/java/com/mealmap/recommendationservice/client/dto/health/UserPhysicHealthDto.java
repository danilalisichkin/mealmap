package com.mealmap.recommendationservice.client.dto.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPhysicHealthDto {
    private UUID userId;

    private Integer weight;

    private Integer height;

    private LocalDate birthDate;

    private String gender;
}
