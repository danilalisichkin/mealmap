package com.mealmap.productservice.validator;

import com.mealmap.productservice.exception.ConflictException;
import com.mealmap.productservice.exception.ResourceNotFoundException;
import com.mealmap.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.productservice.core.message.ApplicationMessages.CATEGORY_NOT_FOUND;
import static com.mealmap.productservice.core.message.ApplicationMessages.CATEGORY_WITH_NAME_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class CategoryValidator {
    private final CategoryRepository categoryRepository;

    public void validateNameUniqueness(String productName) {
        if (categoryRepository.existsByName(productName)) {
            throw new ConflictException(CATEGORY_WITH_NAME_ALREADY_EXISTS);
        }
    }

    public void validateExistenceOfCategoryWithId(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND.formatted(id));
        }
    }
}
