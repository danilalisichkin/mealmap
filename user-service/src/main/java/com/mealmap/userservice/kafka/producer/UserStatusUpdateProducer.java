package com.mealmap.userservice.kafka.producer;

import com.mealmap.userservice.kafka.config.KafkaTopicConfig;
import com.mealmap.userservice.kafka.dto.KafkaUserStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStatusUpdateProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserStatusUpdateDto> kafkaTemplate;

    public void sendMessage(KafkaUserStatusUpdateDto dto) {
        Message<KafkaUserStatusUpdateDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserStatusUpdate()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
