package com.mealmap.preferenceservice.service.impl;

import com.mealmap.preferenceservice.cache.config.CacheConfig;
import com.mealmap.preferenceservice.core.mapper.UserPreferenceMapper;
import com.mealmap.preferenceservice.entity.UserPreference;
import com.mealmap.preferenceservice.service.UserPreferenceRedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.mealmap.preferenceservice.cache.constant.CachePrefixes.CATEGORIES;
import static com.mealmap.preferenceservice.cache.constant.CachePrefixes.PRODUCTS;

@Service
@RequiredArgsConstructor
public class UserPreferenceRedisCacheServiceImpl implements UserPreferenceRedisCacheService {
    private final CacheConfig cacheConfig;

    private final ValueOperations<String, Object> valueOperations;

    private final RedisTemplate<String, Object> redisTemplate;

    private final UserPreferenceMapper userPreferenceMapper;

    @Override
    @Transactional
    public void updateUserPreferenceWithProductPreferences(UUID userId, UserPreference userPreference) {
        var userPreferenceDto = userPreferenceMapper.entityToDto(userPreference);
        String key = generateKeyForUserPreference(userId);
        valueOperations.set(key, userPreferenceDto);

        String wildcardKey = generateWildcardKeyForProductPreferences(userId);
        deleteKeysWithWildcard(wildcardKey);
    }

    @Override
    @Transactional
    public void updateUserPreferenceWithCategoryPreferences(UUID userId, UserPreference userPreference) {
        var userPreferenceDto = userPreferenceMapper.entityToDto(userPreference);
        String key = generateKeyForUserPreference(userId);
        valueOperations.set(key, userPreferenceDto);

        String wildcardKey = generateWildcardKeyForCategoryPreferences(userId);
        deleteKeysWithWildcard(wildcardKey);
    }

    private void deleteKeysWithWildcard(String key) {
        var keys = redisTemplate.keys(key);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    private String generateKeyForUserPreference(UUID userId) {
        return "%s::%s".formatted(cacheConfig.getUserPreference().getName(), userId);
    }

    private String generateWildcardKeyForProductPreferences(UUID userId) {
        return "%s::%s_%s_*".formatted(cacheConfig.getUserPreference().getName(), userId, PRODUCTS);
    }

    private String generateWildcardKeyForCategoryPreferences(UUID userId) {
        return "%s::%s_%s_*".formatted(cacheConfig.getUserPreference().getName(), userId, CATEGORIES);
    }
}
