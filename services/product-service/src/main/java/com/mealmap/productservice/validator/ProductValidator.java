package com.mealmap.productservice.validator;

import com.mealmap.productservice.repository.ProductRepository;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.productservice.core.message.ApplicationMessages.PRODUCT_NOT_FOUND;
import static com.mealmap.productservice.core.message.ApplicationMessages.PRODUCT_WITH_NAME_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class ProductValidator {
    private final ProductRepository productRepository;

    public void validateNameUniqueness(String productName) {
        if (productRepository.existsByName(productName)) {
            throw new ConflictException(PRODUCT_WITH_NAME_ALREADY_EXISTS);
        }
    }

    public void validateExistenceOfProductWithId(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND.formatted(id));
        }
    }
}
