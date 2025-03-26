package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.client.PreferenceApiClient;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import com.mealmap.recommendationservice.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {
    private final PreferenceApiClient preferenceApiClient;

    @Override
    public Preferences getUserPreferences(UUID userId) {
        return preferenceApiClient.getUserPreferences(userId);
    }
}
