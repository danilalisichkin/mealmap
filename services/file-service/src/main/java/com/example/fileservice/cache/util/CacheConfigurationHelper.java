package com.example.fileservice.cache.util;

import com.example.fileservice.cache.properties.CacheProperties;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CacheConfigurationHelper {
    private final RedisSerializer<String> keySerializer = new StringRedisSerializer();

    private final RedisSerializer<byte[]> valueSerializer = RedisSerializer.byteArray();

    public RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(valueSerializer));
    }

    public RedisCacheConfiguration cacheConfiguration(CacheProperties.Cache cache) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(cache.getTtl()))
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(valueSerializer));
    }
}
