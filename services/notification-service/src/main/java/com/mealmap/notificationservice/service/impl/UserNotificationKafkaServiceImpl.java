package com.mealmap.notificationservice.service.impl;

import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.exception.InternalErrorException;
import com.mealmap.notificationservice.kafka.dto.KafkaNotificationCreationDto;
import com.mealmap.notificationservice.kafka.mapper.NotificationKafkaMapper;
import com.mealmap.notificationservice.repository.UserContactsRepository;
import com.mealmap.notificationservice.service.UserNotificationKafkaService;
import com.mealmap.notificationservice.strategy.manager.NotificationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_CONTACTS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserNotificationKafkaServiceImpl implements UserNotificationKafkaService {
    private final NotificationManager notificationManager;

    private final NotificationKafkaMapper notificationKafkaMapper;

    private final UserContactsRepository userContactsRepository;

    @Override
    public void createNotification(KafkaNotificationCreationDto notificationDto) {
        UserContacts userContacts = getUserContactsDoc(notificationDto.getUserId().toString());

        try {
            var creationDto = notificationKafkaMapper.kafkaDtoToCreationDto(notificationDto);
            notificationManager.processNotificationCreation(userContacts, creationDto);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    private UserContacts getUserContactsDoc(String userId) {
        return userContactsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new InternalErrorException(USER_CONTACTS_NOT_FOUND.formatted(userId)));
    }
}
