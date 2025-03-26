package com.mealmap.preferenceservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.preferenceservice.kafka.dto.KafkaUserPreferencesCreationDto;
import com.mealmap.preferenceservice.service.UserPreferencesKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserPreferencesCreationConsumer {
    private final UserPreferencesKafkaService userPreferencesKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-preferences-creation.name}",
            groupId = "${business.kafka.topics.user-preferences-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserPreferencesCreationDto.class);

        userPreferencesKafkaService.createPreferences(dto);
    }
}
