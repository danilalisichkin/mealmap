package com.mealmap.recommendationservice.client;

import com.mealmap.recommendationservice.client.config.FeignJacksonMappingConfig;
import com.mealmap.recommendationservice.client.config.FeignOAuth2Config;
import com.mealmap.recommendationservice.client.config.ProductApiClientConfig;
import com.mealmap.recommendationservice.core.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "${business.services.product.name}",
        path = "${business.services.product.path}",
        contextId = "productClient",
        configuration = {ProductApiClientConfig.class, FeignOAuth2Config.class, FeignJacksonMappingConfig.class})
public interface ProductApiClient {
    @GetMapping("/all")
    List<Product> getAllProducts();
}
