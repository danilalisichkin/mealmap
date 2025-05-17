package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.client.dto.enums.PreferenceType;
import com.mealmap.recommendationservice.core.model.preference.CategoryPreference;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import com.mealmap.recommendationservice.core.model.preference.ProductPreference;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PreferenceFilter {

    public List<ProductPreference> filterProductPreferences(Preferences preferences, PreferenceType type) {
        if (preferences.getProductPreferences() == null)
            return Collections.emptyList();

        return preferences.getProductPreferences()
                .stream()
                .filter(p -> p.getPreferenceType() == type)
                .toList();
    }

    public List<CategoryPreference> filterCategoryPreferences(Preferences preferences, PreferenceType type) {
        if (preferences.getCategoryPreference() == null)
            return Collections.emptyList();

        return preferences.getCategoryPreference()
                .stream()
                .filter(c -> c.getPreferenceType() == type)
                .toList();
    }
}