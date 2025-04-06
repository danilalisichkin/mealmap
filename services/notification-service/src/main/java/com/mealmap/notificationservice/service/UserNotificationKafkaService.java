package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.kafka.dto.KafkaNotificationCreationDto;

public interface UserNotificationKafkaService {
    void createNotification(KafkaNotificationCreationDto notificationDto);
}
