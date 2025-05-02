package com.mealmap.userservice.kafka.producer;

import com.mealmap.userservice.kafka.config.KafkaTopicConfig;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContactsUpdateProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserContactsUpdateDto> kafkaTemplate;

    public void sendMessage(KafkaUserContactsUpdateDto dto) {
        Message<KafkaUserContactsUpdateDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserContactsUpdate()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
