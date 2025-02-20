package com.mealmap.userservice.validator;

import com.mealmap.userservice.exception.ConflictException;
import com.mealmap.userservice.exception.ResourceNotFoundException;
import com.mealmap.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_WITH_EMAIL_ALREADY_EXISTS;
import static com.mealmap.userservice.core.message.ApplicationMessages.USER_WITH_PHONE_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validatePhoneNumberUniqueness(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ConflictException(USER_WITH_PHONE_ALREADY_EXISTS);
        }
    }

    public void validateEmailUniqueness(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException(USER_WITH_EMAIL_ALREADY_EXISTS);
        }
    }
}
