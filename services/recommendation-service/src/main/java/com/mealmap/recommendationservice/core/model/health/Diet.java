package com.mealmap.recommendationservice.core.model.health;

import com.mealmap.recommendationservice.client.dto.enums.DietType;
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
    private DietType type;

    private Integer goalWeight;

    private LocalDate startDate;
}
