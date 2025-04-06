package com.mealmap.userservice.service;

import com.mealmap.userservice.kafka.dto.KafkaUserPreferencesCreationDto;

public interface UserPreferencesKafkaService {
    void createUserPreferences(KafkaUserPreferencesCreationDto preferencesDto);
}
