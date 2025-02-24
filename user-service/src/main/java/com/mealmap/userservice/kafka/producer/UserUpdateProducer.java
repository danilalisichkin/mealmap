package com.mealmap.userservice.kafka.producer;

import com.mealmap.userservice.kafka.config.KafkaTopicConfig;
import com.mealmap.userservice.kafka.dto.KafkaUserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserUpdateDto> kafkaTemplate;

    public void sendMessage(KafkaUserUpdateDto dto) {
        Message<KafkaUserUpdateDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserUpdate()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
