package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateTgChatDto;

public interface UserContactsKafkaService {
    void createUserContacts(KafkaUserContactsCreationDto dto);

    void updateUserTgChatId(KafkaUserContactsUpdateTgChatDto dto);
}
