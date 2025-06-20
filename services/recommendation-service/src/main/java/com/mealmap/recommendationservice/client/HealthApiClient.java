package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.FeignJacksonMappingConfig;
import com.mealmap.recommendationservice.client.config.FeignOAuth2Config;
import com.mealmap.recommendationservice.client.config.HealthApiClientConfig;
import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import com.mealmap.recommendationservice.core.model.health.UserAllergen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "${business.services.health.name}",
        path = "${business.services.health.path}",
        contextId = "healthClient",
        configuration = {HealthApiClientConfig.class, FeignOAuth2Config.class, FeignJacksonMappingConfig.class})
public interface HealthApiClient {
    @GetMapping("/{userId}/physic-health")
    PhysicHealth getUserPhysicHealth(@PathVariable UUID userId);

    @GetMapping("/{userId}/diet")
    Diet getUserDiet(@PathVariable UUID userId);

    @GetMapping("/{userId}/allergens")
    List<UserAllergen> getUserAllergens(@PathVariable UUID userId);
}
