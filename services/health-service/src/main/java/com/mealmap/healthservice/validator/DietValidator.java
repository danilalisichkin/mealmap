package com.mealmap.healthservice.validator;

import com.mealmap.healthservice.entity.PhysicHealth;
import com.mealmap.healthservice.repository.DietRepository;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_DIET_ALREADY_EXISTS;
import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_DIET_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class DietValidator {
    private final DietRepository dietRepository;

    public void validateDietUniqueness(PhysicHealth physicHealth) {
        if (dietRepository.existsByPhysicHealth(physicHealth)) {
            throw new ConflictException(USER_DIET_ALREADY_EXISTS.formatted(physicHealth.getUserId()));
        }
    }

    public void validateDietExistence(PhysicHealth physicHealth) {
        if (physicHealth.getDiet() == null) {
            throw new ResourceNotFoundException(USER_DIET_NOT_FOUND.formatted(physicHealth.getUserId()));
        }
    }
}
