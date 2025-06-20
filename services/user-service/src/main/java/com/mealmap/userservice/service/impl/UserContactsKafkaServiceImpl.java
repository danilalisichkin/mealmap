package com.mealmap.userservice.service.impl;

import com.mealmap.userservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsUpdateDto;
import com.mealmap.userservice.kafka.producer.UserContactsCreationProducer;
import com.mealmap.userservice.kafka.producer.UserContactsUpdateProducer;
import com.mealmap.userservice.service.UserContactsKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContactsKafkaServiceImpl implements UserContactsKafkaService {
    private final UserContactsUpdateProducer userContactsUpdateProducer;

    private final UserContactsCreationProducer userContactsCreationProducer;

    @Override
    public void createUserContacts(KafkaUserContactsCreationDto dto) {
        userContactsCreationProducer.sendMessage(dto);
    }

    @Override
    public void updateUserContacts(KafkaUserContactsUpdateDto dto) {
        userContactsUpdateProducer.sendMessage(dto);
    }
}
