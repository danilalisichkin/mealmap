package com.mealmap.recommendationservice.core.model.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPreference {
    private Long categoryId;

    private String preferenceType;
}
