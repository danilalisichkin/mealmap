package com.mealmap.telegrambot.kafka.producer;

import com.mealmap.telegrambot.kafka.config.KafkaTopicConfig;
import com.mealmap.telegrambot.kafka.dto.KafkaUserContactsUpdateTgChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContactsUpdateTgChatProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, KafkaUserContactsUpdateTgChatDto> kafkaTemplate;

    public void sendMessage(KafkaUserContactsUpdateTgChatDto dto) {
        Message<KafkaUserContactsUpdateTgChatDto> message = MessageBuilder
                .withPayload(dto)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getUserContactsUpdateTgChat()
                                .getName())
                .build();

        kafkaTemplate.send(message);
    }
}
