package com.mealmap.preferenceservice.service;

import com.mealmap.preferenceservice.entity.UserPreference;

import java.util.UUID;

public interface UserPreferenceRedisCacheService {
    void updateUserPreferenceWithProductPreferences(UUID userId, UserPreference userPreference);

    void updateUserPreferenceWithCategoryPreferences(UUID userId, UserPreference userPreference);
}
