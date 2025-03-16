package com.mealmap.recommendationservice.core.model.health;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diet {
    private String dietType;

    private String goalWeight;

    private LocalDate startDate;
}
