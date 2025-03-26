package com.mealmap.recommendationservice.client.dto.preference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPreferenceDto {
    private Long id;

    private Long categoryId;

    private String preferenceType;
}
