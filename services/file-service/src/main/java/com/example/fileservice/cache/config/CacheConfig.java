package com.example.fileservice.cache.config;

import com.example.fileservice.cache.properties.CacheProperties;
import com.example.fileservice.cache.util.CacheConfigurationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final CacheProperties cacheProperties;

    private final CacheConfigurationHelper cacheConfigHelper;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = cacheConfigHelper.defaultCacheConfiguration();

        var fileCache = cacheProperties.getFile();
        var fileCacheConfig = cacheConfigHelper.cacheConfiguration(fileCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(fileCache.getName(), fileCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
