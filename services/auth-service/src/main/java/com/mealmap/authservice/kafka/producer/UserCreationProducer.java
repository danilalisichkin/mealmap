package com.mealmap.authservice.kafka.producer;

import com.mealmap.authservice.kafka.config.KafkaTopicConfig;
import com.mealmap.authservice.kafka.dto.KafkaUserCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserCreationDto> kafkaTemplate;

    public void sendMessage(KafkaUserCreationDto dto) {
        Message<KafkaUserCreationDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserCreation()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
