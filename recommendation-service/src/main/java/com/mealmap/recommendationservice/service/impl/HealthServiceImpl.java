package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.client.HealthApiClient;
import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {
    private final HealthApiClient healthApiClient;

    @Override
    public PhysicHealth getUserPhysicHealth(UUID userId) {
        return healthApiClient.getUserPhysicHealth(userId);
    }

    @Override
    public Diet getUserDiet(UUID userId) {
        return healthApiClient.getUserDiet(userId);
    }
}
