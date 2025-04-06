package com.mealmap.userservice.validator;

import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_WITH_EMAIL_ALREADY_EXISTS;
import static com.mealmap.userservice.core.message.ApplicationMessages.USER_WITH_ID_ALREADY_EXISTS;
import static com.mealmap.userservice.core.message.ApplicationMessages.USER_WITH_PHONE_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateIdUniqueness(UUID id) {
        if (userRepository.existsById(id)) {
            throw new ConflictException(USER_WITH_ID_ALREADY_EXISTS);
        }
    }

    public void validatePhoneNumberUniqueness(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ConflictException(USER_WITH_PHONE_ALREADY_EXISTS);
        }
    }

    public void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(USER_WITH_EMAIL_ALREADY_EXISTS);
        }
    }
}
