package com.mealmap.authservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.authservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.authservice.sevice.UserKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserStatusUpdateConsumer {
    private final UserKafkaService userKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-status-update.name}",
            groupId = "${business.kafka.topics.user-status-update.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserStatusUpdateDto.class);

        userKafkaService.updateUserStatus(dto);
    }
}
