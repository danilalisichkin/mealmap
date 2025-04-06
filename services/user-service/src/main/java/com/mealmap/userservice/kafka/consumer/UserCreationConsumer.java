package com.mealmap.userservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import com.mealmap.userservice.service.UserKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserCreationConsumer {
    private final UserKafkaService userKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-creation.name}",
            groupId = "${business.kafka.topics.user-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserCreationDto.class);

        userKafkaService.createUser(dto);
    }
}
