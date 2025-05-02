package com.mealmap.notificationservice.service.impl;

import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateDto;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateTgChatDto;
import com.mealmap.notificationservice.kafka.mapper.UserContactsKafkaMapper;
import com.mealmap.notificationservice.repository.UserContactsRepository;
import com.mealmap.notificationservice.service.UserContactsKafkaService;
import com.mealmap.notificationservice.validator.UserContactsValidator;
import com.mealmap.starters.exceptionstarter.exception.InternalErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_CONTACTS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserContactsKafkaServiceImpl implements UserContactsKafkaService {
    private final UserContactsValidator userContactsValidator;

    private final UserContactsKafkaMapper userContactsKafkaMapper;

    private final UserContactsRepository userContactsRepository;

    @Override
    @Transactional
    public void createUserContacts(KafkaUserContactsCreationDto dto) {
        try {
            userContactsValidator.validateUserIdUniqueness(dto.getUserId().toString());

            userContactsRepository.save(
                    userContactsKafkaMapper.dtoToDoc(dto));
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    @Transactional
    public void updateUserContacts(KafkaUserContactsUpdateDto dto) {
        UserContacts contactsToUpdate = getUserContactsDoc(dto.getUserId().toString());

        userContactsKafkaMapper.updateDocFromDto(contactsToUpdate, dto);

        userContactsRepository.save(contactsToUpdate);
    }

    @Override
    @Transactional
    public void updateUserTgChatId(KafkaUserContactsUpdateTgChatDto dto) {
        UserContacts contactsToUpdate = getUserContactsDoc(dto.getUserId().toString());

        userContactsKafkaMapper.updateDocFromDto(contactsToUpdate, dto);

        userContactsRepository.save(contactsToUpdate);
    }

    private UserContacts getUserContactsDoc(String userId) {
        return userContactsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new InternalErrorException(USER_CONTACTS_NOT_FOUND.formatted(userId)));
    }
}
