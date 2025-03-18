package com.mealmap.preferenceservice.service;

import com.mealmap.preferenceservice.entity.UserPreferences;

import java.util.UUID;

public interface UserPreferencesRedisCacheService {
    void updatePreferenceWithProductPreferences(UUID userId, UserPreferences userPreferences);

    void updatePreferenceWithCategoryPreferences(UUID userId, UserPreferences userPreferences);
}
