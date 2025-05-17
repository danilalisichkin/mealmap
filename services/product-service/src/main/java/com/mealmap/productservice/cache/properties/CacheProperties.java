package com.mealmap.productservice.cache.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {
    private final Cache product;
    private final Cache category;
    private final Cache allergen;

    @Getter
    @AllArgsConstructor
    public static class Cache {
        private final String name;
        private final Long ttl;
    }
}
