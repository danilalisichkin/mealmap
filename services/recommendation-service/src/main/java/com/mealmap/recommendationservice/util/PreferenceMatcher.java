package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.core.model.preference.CategoryPreference;
import com.mealmap.recommendationservice.core.model.preference.ProductPreference;
import com.mealmap.recommendationservice.core.model.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreferenceMatcher {
    public boolean isDislikedProduct(Product product, List<ProductPreference> dislikes) {
        return dislikes.stream()
                .anyMatch(dp -> dp.getProductId().equals(product.getId()));
    }

    public boolean isInDislikedCategory(Product product, List<CategoryPreference> dislikes) {
        return product.getCategories().stream()
                .anyMatch(cat -> dislikes.stream()
                        .anyMatch(dc -> dc.getCategoryId().equals(cat.getId())));
    }
}
