package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.ai.constant.PromptTemplates;
import com.mealmap.recommendationservice.client.dto.enums.PreferenceType;
import com.mealmap.recommendationservice.core.model.PromptData;
import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.core.model.health.UserAllergen;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import com.mealmap.recommendationservice.core.model.product.Product;
import com.mealmap.recommendationservice.service.HealthService;
import com.mealmap.recommendationservice.service.OrderService;
import com.mealmap.recommendationservice.service.PreferenceService;
import com.mealmap.recommendationservice.service.ProductService;
import com.mealmap.recommendationservice.service.PromptBuildingService;
import com.mealmap.recommendationservice.util.CatalogFilter;
import com.mealmap.recommendationservice.util.HealthInfoComposer;
import com.mealmap.recommendationservice.util.JsonSerializer;
import com.mealmap.recommendationservice.util.PreferenceFilter;
import com.mealmap.recommendationservice.validator.CatalogValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptBuildingServiceImpl implements PromptBuildingService {
    private static final String NO_ALLERGIES = "не указаны";

    private final JsonSerializer jsonSerializer;

    private final HealthInfoComposer healthInfoComposer;

    private final CatalogFilter catalogFilter;

    private final PreferenceFilter preferenceFilter;

    private final CatalogValidator catalogValidator;

    private final HealthService healthService;

    private final PreferenceService preferenceService;

    private final OrderService orderService;

    private final ProductService productService;

    @Override
    public String buildPromptUserMessage(UUID userId) {
        List<Product> catalog = productService.getAllProducts();
        Preferences preferences = preferenceService.getUserPreferences(userId);
        PhysicHealth physicHealth = healthService.getUserPhysicHealth(userId);
        Diet diet = healthService.getUserDiet(userId);
        List<UserAllergen> userAllergens = healthService.getUserAllergens(userId);

        filterCatalog(catalog, preferences, userAllergens);

        PromptData promptData = composePromptData(userId, preferences, catalog, physicHealth, diet);

        return String.format(
                PromptTemplates.USER_RECOMMENDATIONS,
                promptData.catalogJson(),
                promptData.likedProductsJson(),
                promptData.likedCategoriesJson(),
                promptData.ordersJson(),
                promptData.allergies(),
                promptData.healthInfo());
    }

    private void filterCatalog(List<Product> catalog, Preferences preferences, List<UserAllergen> allergens) {
        catalogValidator.validateCatalogFill(catalog);

        var dislikedProducts = preferenceFilter.filterProductPreferences(preferences, PreferenceType.DISLIKED);
        var dislikedCategories = preferenceFilter.filterCategoryPreferences(preferences, PreferenceType.DISLIKED);

        catalogFilter.filterCatalog(
                catalog,
                dislikedProducts,
                dislikedCategories,
                allergens
        );
    }

    private PromptData composePromptData(
            UUID userId, Preferences preferences, List<Product> filteredCatalog,
            PhysicHealth physicHealth, Diet diet) {

        return new PromptData(
                jsonSerializer.serialize(filteredCatalog),
                jsonSerializer.serialize(preferenceFilter.filterProductPreferences(preferences, PreferenceType.LIKED)),
                jsonSerializer.serialize(preferenceFilter.filterCategoryPreferences(preferences, PreferenceType.LIKED)),
                jsonSerializer.serialize(orderService.getUserLastOrders(userId)),
                NO_ALLERGIES,
                healthInfoComposer.composeHealthInfo(physicHealth, diet)
        );
    }
}
