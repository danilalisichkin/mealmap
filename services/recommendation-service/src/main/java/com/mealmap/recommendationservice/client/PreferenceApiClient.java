package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.FeignOAuth2Config;
import com.mealmap.recommendationservice.client.config.PreferenceApiClientConfig;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "preference-api",
        url = "${business.services.preference.url}",
        contextId = "preferenceClient",
        configuration = {PreferenceApiClientConfig.class, FeignOAuth2Config.class})
public interface PreferenceApiClient {
    @GetMapping("/{userId}/preferences")
    Preferences getUserPreferences(@PathVariable UUID userId);
}
