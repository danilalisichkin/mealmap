package com.mealmap.productservice.validator;

import com.mealmap.productservice.repository.AllergenRepository;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.productservice.core.message.ApplicationMessages.ALLERGEN_NOT_FOUND;
import static com.mealmap.productservice.core.message.ApplicationMessages.ALLERGEN_WITH_NAME_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class AllergenValidator {
    private final AllergenRepository allergenRepository;

    public void validateNameUniqueness(String allergenName) {
        if (allergenRepository.existsByName(allergenName)) {
            throw new ConflictException(ALLERGEN_WITH_NAME_ALREADY_EXISTS);
        }
    }

    public void validateExistenceOfAllergenWithId(Long id) {
        if (!allergenRepository.existsById(id)) {
            throw new ResourceNotFoundException(ALLERGEN_NOT_FOUND.formatted(id));
        }
    }
}
