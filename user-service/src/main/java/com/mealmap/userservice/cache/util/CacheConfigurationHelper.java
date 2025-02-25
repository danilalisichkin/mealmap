package com.mealmap.userservice.cache.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mealmap.userservice.cache.config.CacheConfig;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CacheConfigurationHelper {
    private final ObjectMapper objectMapper;

    private final RedisSerializer<Object> redisSerializer;

    public CacheConfigurationHelper() {
        this.objectMapper = buildObjectMapper();
        this.redisSerializer = buildRedisSerializer();
    }

    public RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(redisSerializer));
    }

    public RedisCacheConfiguration cacheConfiguration(CacheConfig.Cache cache) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(cache.getTtl()))
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(redisSerializer));
    }

    private RedisSerializer<Object> buildRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);

        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
