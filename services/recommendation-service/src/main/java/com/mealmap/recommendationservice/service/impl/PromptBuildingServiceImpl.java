package com.mealmap.recommendationservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.recommendationservice.core.model.UserInfo;
import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import com.mealmap.recommendationservice.exception.ResourceNotFoundException;
import com.mealmap.recommendationservice.service.HealthService;
import com.mealmap.recommendationservice.service.OrderService;
import com.mealmap.recommendationservice.service.PreferenceService;
import com.mealmap.recommendationservice.service.ProductService;
import com.mealmap.recommendationservice.service.PromptBuildingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.mealmap.recommendationservice.ai.constant.PromptTemplates.USER_RECOMMENDATIONS;

@Service
@RequiredArgsConstructor
public class PromptBuildingServiceImpl implements PromptBuildingService {
    private final ObjectMapper objectMapper;

    private final HealthService healthService;

    private final PreferenceService preferenceService;

    private final OrderService orderService;

    private final ProductService productService;

    @Override
    @SneakyThrows
    public String buildPromptUserMessage(UUID userId) {
        var menu = productService.getAllProducts();

        var userPhysicHealth = getUserPhysicHealth(userId);
        var userDiet = getUserDiet(userId);
        var userPreferences = getUserPreferences(userId);
        var userLastOrders = orderService.getUserLastOrders(userId);

        var userInfo = UserInfo.builder()
                .userId(userId.toString())
                .physicHealth(userPhysicHealth)
                .diet(userDiet)
                .preferences(userPreferences)
                .lastOrders(userLastOrders)
                .build();

        var menuAsString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(menu);
        var userInfoAsJson = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userInfo);

        return String.format(USER_RECOMMENDATIONS, menuAsString, userInfoAsJson);
    }

    private PhysicHealth getUserPhysicHealth(UUID userId) {
        try {
            return healthService.getUserPhysicHealth(userId);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }

    private Diet getUserDiet(UUID userId) {
        try {
            return healthService.getUserDiet(userId);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }

    private Preferences getUserPreferences(UUID userId) {
        try {
            return preferenceService.getUserPreferences(userId);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }
}
