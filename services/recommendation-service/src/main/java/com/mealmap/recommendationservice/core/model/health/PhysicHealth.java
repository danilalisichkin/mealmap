package com.mealmap.recommendationservice.core.model.health;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicHealth {
    private String weight;

    private String height;

    private String birthDate;

    private String gender;
}
