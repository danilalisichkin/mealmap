package com.mealmap.productservice.cache.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.cache")
public class CacheConfig {
    private final Cache product;
    private final Cache category;

    @Getter
    @AllArgsConstructor
    public static class Cache {
        private final String name;
        private final Long ttl;
    }
}
