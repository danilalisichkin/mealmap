package com.mealmap.orderservice.client;

import com.mealmap.orderservice.client.config.FeignOAuth2Config;
import com.mealmap.orderservice.client.config.ProductApiClientConfig;
import com.mealmap.orderservice.client.dto.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "product-api",
        url = "${business.services.product.url}",
        contextId = "productClient",
        configuration = {ProductApiClientConfig.class, FeignOAuth2Config.class})
public interface ProductApiClient {
    @GetMapping("/bulk")
    List<ProductDto> bulkGetProducts(@RequestParam List<Long> ids);
}
