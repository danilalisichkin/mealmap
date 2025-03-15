package com.mealmap.healthservice.core.dto.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPhysicHealthUpdatingDto {
    private Integer weight;

    private Integer height;
}
