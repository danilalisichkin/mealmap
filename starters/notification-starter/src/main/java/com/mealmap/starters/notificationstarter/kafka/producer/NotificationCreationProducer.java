package com.mealmap.starters.notificationstarter.kafka.producer;

import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.properties.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationCreationProducer {
    private final KafkaTopicConfig topicConfig;

    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendMessage(Notification notification) {
        Message<Notification> message = MessageBuilder
                .withPayload(notification)
                .setHeader(
                        KafkaHeaders.TOPIC,
                        topicConfig.getName())
                .build();

        kafkaTemplate.send(message);
    }
}