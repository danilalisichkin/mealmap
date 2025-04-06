package com.mealmap.recommendationservice.client.dto.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDietDto {
    private String dietType;

    private Integer goalWeight;

    private LocalDate startDate;
}
