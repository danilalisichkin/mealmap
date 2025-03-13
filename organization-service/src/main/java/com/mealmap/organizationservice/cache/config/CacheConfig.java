package com.mealmap.organizationservice.cache.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.cache")
public class CacheConfig {
    private final Cache organization;

    @Getter
    @AllArgsConstructor
    public static class Cache {
        private final String name;
        private final Long ttl;
    }
}
