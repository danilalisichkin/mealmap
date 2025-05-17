package com.mealmap.productservice.cache.config;

import com.mealmap.productservice.cache.properties.CacheProperties;
import com.mealmap.productservice.cache.util.CacheConfigurationHelper;
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

        var productCache = cacheProperties.getProduct();
        var productCacheConfig = cacheConfigHelper.cacheConfiguration(productCache);

        var categoryCache = cacheProperties.getCategory();
        var categoryCacheConfig = cacheConfigHelper.cacheConfiguration(categoryCache);

        var allergenCache = cacheProperties.getAllergen();
        var allergenCacheConfig = cacheConfigHelper.cacheConfiguration(allergenCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(productCache.getName(), productCacheConfig)
                .withCacheConfiguration(categoryCache.getName(), categoryCacheConfig)
                .withCacheConfiguration(allergenCache.getName(), allergenCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
