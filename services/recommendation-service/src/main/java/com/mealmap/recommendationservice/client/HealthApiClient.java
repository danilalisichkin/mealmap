package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.HealthApiClientConfig;
import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "user-health-api",
        url = "${business.services.health.url}",
        contextId = "healthClient",
        configuration = HealthApiClientConfig.class)
public interface HealthApiClient {
    @GetMapping("/{userId}/physic-health")
    PhysicHealth getUserPhysicHealth(@PathVariable UUID userId);

    @GetMapping("/{userId}/diet")
    Diet getUserDiet(@PathVariable UUID userId);
}
