package com.mealmap.userservice.service.impl;

import com.mealmap.userservice.kafka.dto.KafkaUserPreferencesCreationDto;
import com.mealmap.userservice.kafka.producer.UserPreferencesProducer;
import com.mealmap.userservice.service.UserPreferencesKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferencesKafkaServiceImpl implements UserPreferencesKafkaService {
    private final UserPreferencesProducer userPreferencesProducer;

    @Override
    public void createUserPreferences(KafkaUserPreferencesCreationDto preferencesDto) {
        userPreferencesProducer.sendMessage(preferencesDto);
    }
}
