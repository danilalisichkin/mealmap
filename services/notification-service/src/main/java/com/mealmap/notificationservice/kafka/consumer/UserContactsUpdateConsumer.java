package com.mealmap.notificationservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateDto;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateTgChatDto;
import com.mealmap.notificationservice.service.UserContactsKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserContactsUpdateConsumer {
    private final UserContactsKafkaService userContactsKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-contacts-update.name}",
            groupId = "${business.kafka.topics.user-contacts-update.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserContactsUpdateDto.class);

        userContactsKafkaService.updateUserContacts(dto);
    }
}
