package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.core.model.health.UserAllergen;

import java.util.List;
import java.util.UUID;

public interface HealthService {
    PhysicHealth getUserPhysicHealth(UUID userId);

    Diet getUserDiet(UUID userId);

    List<UserAllergen> getUserAllergens(UUID userId);
}
