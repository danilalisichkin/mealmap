package com.mealmap.orderservice.client;

import com.mealmap.orderservice.client.config.FeignOAuth2Config;
import com.mealmap.orderservice.client.config.PromoStatApiClientConfig;
import com.mealmap.orderservice.client.dto.promo.PromoStatCreationDto;
import com.mealmap.orderservice.client.dto.promo.PromoStatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "promo-stat-api",
        url = "${business.services.promo-stat.url}",
        contextId = "promoStatClient",
        configuration = {PromoStatApiClientConfig.class, FeignOAuth2Config.class})
public interface PromoStatApiClient {
    @PostMapping
    PromoStatDto createPromoStat(@RequestBody PromoStatCreationDto statDto);
}
