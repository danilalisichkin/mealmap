package com.mealmap.healthservice.validator;

import com.mealmap.healthservice.entity.Allergen;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_ALLERGEN_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class AllergenValidator {
    public void validateAllergenUniqueness(List<Allergen> allergens, Long allergenId) {
        boolean isAllergenAlreadySet = isAllergenInAllergenList(allergens, allergenId);

        if (isAllergenAlreadySet) {
            throw new ConflictException(USER_ALLERGEN_ALREADY_EXISTS);
        }
    }

    private boolean isAllergenInAllergenList(List<Allergen> allergens, Long allergenId) {
        return allergens.stream()
                .anyMatch(allergen -> allergen
                        .getAllergenId().equals(allergenId));
    }
}
