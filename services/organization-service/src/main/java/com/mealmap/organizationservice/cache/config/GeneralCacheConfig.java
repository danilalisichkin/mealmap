package com.mealmap.organizationservice.cache.config;

import com.mealmap.organizationservice.cache.properties.CacheProperties;
import com.mealmap.organizationservice.cache.util.CacheConfigurationHelper;
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
    private final CacheProperties cacheProperties;

    private final CacheConfigurationHelper cacheConfigHelper;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = cacheConfigHelper.defaultCacheConfiguration();

        var organizationCache = cacheProperties.getOrganization();
        var organizationCacheConfig = cacheConfigHelper.cacheConfiguration(organizationCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(organizationCache.getName(), organizationCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
