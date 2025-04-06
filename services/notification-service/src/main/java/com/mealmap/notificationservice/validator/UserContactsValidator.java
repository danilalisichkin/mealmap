package com.mealmap.notificationservice.validator;

import com.mealmap.notificationservice.exception.ConflictException;
import com.mealmap.notificationservice.repository.UserContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_CONTACTS_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class UserContactsValidator {
    private final UserContactsRepository userContactsRepository;

    public void validateUserIdUniqueness(String userId) {
        if (userContactsRepository.existsByUserId(userId)) {
            throw new ConflictException(USER_CONTACTS_ALREADY_EXISTS.formatted(userId));
        }
    }
}
