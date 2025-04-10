package com.mealmap.recommendationservice.client.dto.health;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDietDto {
    private String dietType;

    private Integer goalWeight;

    private LocalDate startDate;
}
