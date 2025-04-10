package com.mealmap.orderservice.client;

import com.mealmap.orderservice.client.config.FeignOAuth2Config;
import com.mealmap.orderservice.client.config.PromoCodeApiClientConfig;
import com.mealmap.orderservice.client.dto.promo.PromoCodeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "promo-code-api",
        url = "${business.services.promo-code.url}",
        contextId = "promoCodeClient",
        configuration = {PromoCodeApiClientConfig.class, FeignOAuth2Config.class})
public interface PromoCodeApiClient {
    @GetMapping("/{code}")
    PromoCodeDto getPromoCode(@PathVariable String code);
}
