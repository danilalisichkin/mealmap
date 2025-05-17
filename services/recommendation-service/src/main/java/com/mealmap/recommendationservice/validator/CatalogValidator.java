package com.mealmap.recommendationservice.validator;

import com.mealmap.recommendationservice.core.model.product.Product;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mealmap.recommendationservice.core.message.ApplicationMessages.USER_MENU_IS_EMPTY;

@Component
public class CatalogValidator {
    public void validateCatalogFill(List<Product> catalog) {
        if (catalog.isEmpty()) {
            throw new ResourceNotFoundException(USER_MENU_IS_EMPTY);
        }
    }
}
