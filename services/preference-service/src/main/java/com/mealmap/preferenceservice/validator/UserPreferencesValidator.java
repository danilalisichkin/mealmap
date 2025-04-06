package com.mealmap.preferenceservice.validator;

import com.mealmap.preferenceservice.entity.CategoryPreference;
import com.mealmap.preferenceservice.entity.ProductPreference;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import static com.mealmap.preferenceservice.core.message.ApplicationMessages.CATEGORY_PREFERENCE_ALREADY_EXISTS;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.CATEGORY_PREFERENCE_NOT_FOUND;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PRODUCT_PREFERENCE_ALREADY_EXISTS;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PRODUCT_PREFERENCE_NOT_FOUND;

@Component
public class UserPreferencesValidator {

    public void validateProductPreferenceUniqueness(List<ProductPreference> productPreferences, Long productId) {
        validateUniqueness(productPreferences, pp -> pp.getProductId().equals(productId),
                PRODUCT_PREFERENCE_ALREADY_EXISTS.formatted(productId));
    }

    public void validateCategoryPreferenceUniqueness(List<CategoryPreference> categoryPreferences, Long categoryId) {
        validateUniqueness(categoryPreferences, cp -> cp.getCategoryId().equals(categoryId),
                CATEGORY_PREFERENCE_ALREADY_EXISTS.formatted(categoryId));
    }

    public void validateProductPreferenceExistence(List<ProductPreference> productPreferences, Long id) {
        validateExistence(productPreferences, pp -> pp.getId().equals(id),
                PRODUCT_PREFERENCE_NOT_FOUND.formatted(id));
    }

    public void validateCategoryPreferenceExistence(List<CategoryPreference> categoryPreferences, Long id) {
        validateExistence(categoryPreferences, cp -> cp.getId().equals(id),
                CATEGORY_PREFERENCE_NOT_FOUND.formatted(id));
    }

    private <T> void validateUniqueness(List<T> preferences, Predicate<T> predicate, String errorMessage) {
        if (preferences.stream().anyMatch(predicate)) {
            throw new ConflictException(errorMessage);
        }
    }

    private <T> void validateExistence(List<T> preferences, Predicate<T> predicate, String errorMessage) {
        if (preferences.stream().noneMatch(predicate)) {
            throw new ResourceNotFoundException(errorMessage);
        }
    }
}
