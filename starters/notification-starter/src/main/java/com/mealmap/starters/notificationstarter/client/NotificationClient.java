package com.mealmap.starters.notificationstarter.client;

import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.kafka.producer.NotificationCreationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationClient {
    private final NotificationCreationProducer producer;

    public void sendNotification(Notification notification) {
        producer.sendMessage(notification);
    }
}
