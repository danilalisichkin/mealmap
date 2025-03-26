package com.mealmap.healthservice.validator;

import com.mealmap.healthservice.exception.ConflictException;
import com.mealmap.healthservice.repository.PhysicHealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_PHYSICAL_HEALTH_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class PhysicHealthValidator {
    private final PhysicHealthRepository physicHealthRepository;

    public void validateUserIdUniqueness(UUID userId) {
        if (physicHealthRepository.existsByUserId(userId)) {
            throw new ConflictException(USER_PHYSICAL_HEALTH_ALREADY_EXISTS.formatted(userId));
        }
    }
}
