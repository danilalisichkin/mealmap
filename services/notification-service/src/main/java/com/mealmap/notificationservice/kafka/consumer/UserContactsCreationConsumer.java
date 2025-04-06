package com.mealmap.notificationservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.notificationservice.service.UserContactsKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserContactsCreationConsumer {
    private final UserContactsKafkaService userContactsKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-contacts-creation.name}",
            groupId = "${business.kafka.topics.user-contacts-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserContactsCreationDto.class);

        userContactsKafkaService.createUserContacts(dto);
    }
}
