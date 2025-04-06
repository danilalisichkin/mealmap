package com.mealmap.authservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.authservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.authservice.sevice.UserKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserRoleUpdateConsumer {
    private final UserKafkaService userKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.user-role-update.name}",
            groupId = "${business.kafka.topics.user-role-update.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaUserRoleUpdateDto.class);

        userKafkaService.updateUserRole(dto);
    }
}
