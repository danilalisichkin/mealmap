package com.example.fileservice.cache.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {
    private final Cache file;

    @Getter
    @AllArgsConstructor
    public static class Cache {
        private final String name;
        private final Long ttl;
    }
}
