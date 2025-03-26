package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;

import java.util.UUID;

public interface HealthService {
    PhysicHealth getUserPhysicHealth(UUID userId);

    Diet getUserDiet(UUID userId);
}
