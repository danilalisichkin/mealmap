package com.mealmap.preferenceservice.cache.config;

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
public class GeneralCacheConfig {
    private final CacheConfig cacheConfig;

    private final CacheConfigurationHelper cacheConfigHelper;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = cacheConfigHelper.defaultCacheConfiguration();

        CacheConfig.Cache userPreferenceCache = cacheConfig.getUserPreference();
        RedisCacheConfiguration userPreferenceCacheCacheConfig = cacheConfigHelper.cacheConfiguration(userPreferenceCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(userPreferenceCache.getName(), userPreferenceCacheCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
