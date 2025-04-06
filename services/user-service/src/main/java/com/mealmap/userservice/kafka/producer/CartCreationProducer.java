package com.mealmap.userservice.kafka.producer;

import com.mealmap.userservice.kafka.config.KafkaTopicConfig;
import com.mealmap.userservice.kafka.dto.KafkaCartCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartCreationProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserCreationDto> kafkaTemplate;

    public void sendMessage(KafkaCartCreationDto dto) {
        Message<KafkaCartCreationDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getCartCreation()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
