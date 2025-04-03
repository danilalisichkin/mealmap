package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateTgChatDto;

public interface UserContactsKafkaService {
    void updateUserTgChatId(KafkaUserContactsUpdateTgChatDto dto);
}
