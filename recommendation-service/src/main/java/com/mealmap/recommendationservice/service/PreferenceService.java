package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.model.preference.Preferences;

import java.util.UUID;

public interface PreferenceService {
    Preferences getUserPreferences(UUID userId);
}
