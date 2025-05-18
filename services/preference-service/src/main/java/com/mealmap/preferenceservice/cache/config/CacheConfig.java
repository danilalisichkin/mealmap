package com.mealmap.preferenceservice.cache.config;

import com.mealmap.preferenceservice.cache.properties.CacheProperties;
import com.mealmap.preferenceservice.cache.util.CacheConfigurationHelper;
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

        var userPreferenceCache = cacheProperties.getUserPreferences();
        var userPreferenceCacheCacheConfig = cacheConfigHelper.cacheConfiguration(userPreferenceCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(
                        userPreferenceCache.getName(),
                        userPreferenceCacheCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
