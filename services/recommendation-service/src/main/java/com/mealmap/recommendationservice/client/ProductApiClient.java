package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.FeignOAuth2Config;
import com.mealmap.recommendationservice.client.config.ProductApiClientConfig;
import com.mealmap.recommendationservice.client.dto.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "product-api",
        url = "${business.services.product.url}",
        contextId = "productClient",
        configuration = {ProductApiClientConfig.class, FeignOAuth2Config.class})
public interface ProductApiClient {
    @GetMapping("/all")
    List<ProductDto> getAllProducts();
}
