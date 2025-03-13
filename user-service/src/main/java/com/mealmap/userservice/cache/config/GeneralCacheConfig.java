package com.mealmap.userservice.cache.config;

import com.mealmap.userservice.cache.util.CacheConfigurationHelper;
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

        CacheConfig.Cache userProfileCache = cacheConfig.getUserProfile();
        RedisCacheConfiguration userProfileCacheConfig = cacheConfigHelper.cacheConfiguration(userProfileCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(userProfileCache.getName(), userProfileCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
