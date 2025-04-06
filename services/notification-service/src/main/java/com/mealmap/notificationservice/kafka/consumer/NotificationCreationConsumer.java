package com.mealmap.notificationservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.notificationservice.kafka.dto.KafkaNotificationCreationDto;
import com.mealmap.notificationservice.service.UserNotificationKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationCreationConsumer {
    private final UserNotificationKafkaService userNotificationKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.notification-creation.name}",
            groupId = "${business.kafka.topics.notification-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaNotificationCreationDto.class);

        userNotificationKafkaService.createNotification(dto);
    }
}
