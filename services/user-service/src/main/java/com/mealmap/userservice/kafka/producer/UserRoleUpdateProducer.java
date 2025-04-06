package com.mealmap.userservice.kafka.producer;

import com.mealmap.userservice.kafka.config.KafkaTopicConfig;
import com.mealmap.userservice.kafka.dto.KafkaUserRoleUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleUpdateProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserRoleUpdateDto> kafkaTemplate;

    public void sendMessage(KafkaUserRoleUpdateDto dto) {
        Message<KafkaUserRoleUpdateDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserRoleUpdate()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
