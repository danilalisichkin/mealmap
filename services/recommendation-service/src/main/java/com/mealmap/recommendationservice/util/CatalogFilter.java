package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.core.model.preference.CategoryPreference;
import com.mealmap.recommendationservice.core.model.preference.ProductPreference;
import com.mealmap.recommendationservice.core.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CatalogFilter {

    private final PreferenceMatcher preferenceMatcher;

    public List<Product> filterCatalog(List<Product> catalog,
                                       List<ProductPreference> dislikedProducts,
                                       List<CategoryPreference> dislikedCategories) {
        return catalog.stream()
                .filter(product -> !preferenceMatcher.isDislikedProduct(product, dislikedProducts))
                .filter(product -> !preferenceMatcher.isInDislikedCategory(product, dislikedCategories))
                .toList();
    }
}
