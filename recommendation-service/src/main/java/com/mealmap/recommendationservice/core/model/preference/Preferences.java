package com.mealmap.recommendationservice.core.model.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Preferences {
    private List<ProductPreference> productPreferences;

    private List<CategoryPreference> categoryPreference;
}
