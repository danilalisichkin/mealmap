package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.core.model.health.UserAllergen;
import com.mealmap.recommendationservice.core.model.preference.CategoryPreference;
import com.mealmap.recommendationservice.core.model.preference.ProductPreference;
import com.mealmap.recommendationservice.core.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CatalogFilter {

    private final PreferenceMatcher preferenceMatcher;

    public void filterCatalog(List<Product> catalog,
                                       List<ProductPreference> dislikedProducts,
                                       List<CategoryPreference> dislikedCategories,
                                       List<UserAllergen> allergens) {
        Set<Long> userAllergenIds = allergens.stream()
                .map(UserAllergen::getAllergenId)
                .collect(Collectors.toSet());

        catalog.removeIf(product ->
                preferenceMatcher.isDislikedProduct(product, dislikedProducts)
                        || preferenceMatcher.isInDislikedCategory(product, dislikedCategories)
                        || product.getAllergens().stream()
                        .anyMatch(allergen -> userAllergenIds.contains(allergen.getId()))
        );
    }
}
