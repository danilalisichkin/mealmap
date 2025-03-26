package com.mealmap.preferenceservice.service.impl;

import com.mealmap.preferenceservice.kafka.dto.KafkaUserPreferencesCreationDto;
import com.mealmap.preferenceservice.kafka.mapper.UserPreferencesKafkaMapper;
import com.mealmap.preferenceservice.repository.UserPreferencesRepository;
import com.mealmap.preferenceservice.service.UserPreferencesKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPreferencesKafkaServiceImpl implements UserPreferencesKafkaService {
    private final UserPreferencesKafkaMapper userPreferencesKafkaMapper;

    private final UserPreferencesRepository userPreferencesRepository;

    @Override
    @Transactional
    public void createPreferences(KafkaUserPreferencesCreationDto preferencesDto) {
        userPreferencesRepository.save(
                userPreferencesKafkaMapper.dtoToEntity(preferencesDto));
    }
}
