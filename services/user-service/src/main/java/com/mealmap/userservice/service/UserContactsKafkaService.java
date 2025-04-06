package com.mealmap.userservice.service;

import com.mealmap.userservice.kafka.dto.KafkaUserContactsCreationDto;

public interface UserContactsKafkaService {
    void createUserContacts(KafkaUserContactsCreationDto dto);
}
