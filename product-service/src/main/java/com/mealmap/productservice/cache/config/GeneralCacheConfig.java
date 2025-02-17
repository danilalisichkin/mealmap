package com.mealmap.productservice.cache.config;

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
public class GeneralCacheConfig {
    private final CacheConfig cacheConfig;

    private final CacheConfigurationHelper cacheConfigHelper;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = cacheConfigHelper.defaultCacheConfiguration();

        CacheConfig.Cache productCache = cacheConfig.getProduct();
        RedisCacheConfiguration productCacheConfig = cacheConfigHelper.cacheConfiguration(productCache);

        CacheConfig.Cache categoryCache = cacheConfig.getCategory();
        RedisCacheConfiguration categoryCacheConfig = cacheConfigHelper.cacheConfiguration(categoryCache);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(productCache.getName(), productCacheConfig)
                .withCacheConfiguration(categoryCache.getName(), categoryCacheConfig)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
}
