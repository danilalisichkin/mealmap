package com.mealmap.preferenceservice.validator;

import com.mealmap.preferenceservice.entity.CategoryPreference;
import com.mealmap.preferenceservice.entity.ProductPreference;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import static com.mealmap.preferenceservice.core.message.ApplicationMessages.CATEGORY_PREFERENCE_ALREADY_EXISTS;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PRODUCT_PREFERENCE_ALREADY_EXISTS;

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

    private <T> void validateUniqueness(List<T> preferences, Predicate<T> predicate, String errorMessage) {
        if (preferences.stream().anyMatch(predicate)) {
            throw new ConflictException(errorMessage);
        }
    }
}
