package com.mealmap.userservice.service;

import com.mealmap.userservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsUpdateDto;

public interface UserContactsKafkaService {
    void createUserContacts(KafkaUserContactsCreationDto dto);

    void updateUserContacts(KafkaUserContactsUpdateDto dto);
}
