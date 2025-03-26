package com.mealmap.preferenceservice.service;

import com.mealmap.preferenceservice.kafka.dto.KafkaUserPreferencesCreationDto;

public interface UserPreferencesKafkaService {
    void createPreferences(KafkaUserPreferencesCreationDto preferencesDto);
}
