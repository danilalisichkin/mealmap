package com.mealmap.healthservice.validator;

import com.mealmap.healthservice.entity.UserPhysicHealth;
import com.mealmap.healthservice.exception.ConflictException;
import com.mealmap.healthservice.repository.UserDietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_DIET_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class UserDietValidator {
    private final UserDietRepository userDietRepository;

    public void validateDietUniqueness(UserPhysicHealth physicHealth) {
        if (userDietRepository.existsByPhysicHealth(physicHealth)) {
            throw new ConflictException(USER_DIET_ALREADY_EXISTS.formatted(physicHealth.getUserId()));
        }
    }
}
