package com.mealmap.preferenceservice.service.impl;

import com.mealmap.preferenceservice.cache.config.CacheConfig;
import com.mealmap.preferenceservice.core.mapper.UserPreferencesMapper;
import com.mealmap.preferenceservice.entity.UserPreferences;
import com.mealmap.preferenceservice.service.UserPreferencesRedisCacheService;
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
public class UserPreferencesRedisCacheServiceImpl implements UserPreferencesRedisCacheService {
    private final CacheConfig cacheConfig;

    private final ValueOperations<String, Object> valueOperations;

    private final RedisTemplate<String, Object> redisTemplate;

    private final UserPreferencesMapper userPreferencesMapper;

    @Override
    @Transactional
    public void updatePreferenceWithProductPreferences(UUID userId, UserPreferences userPreferences) {
        var userPreferenceDto = userPreferencesMapper.entityToDto(userPreferences);
        String key = generateKeyForUserPreferences(userId);
        valueOperations.set(key, userPreferenceDto);

        String wildcardKey = generateWildcardKeyForProductPreferences(userId);
        deleteKeysWithWildcard(wildcardKey);
    }

    @Override
    @Transactional
    public void updatePreferenceWithCategoryPreferences(UUID userId, UserPreferences userPreferences) {
        var userPreferenceDto = userPreferencesMapper.entityToDto(userPreferences);
        String key = generateKeyForUserPreferences(userId);
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

    private String generateKeyForUserPreferences(UUID userId) {
        return "%s::%s".formatted(cacheConfig.getUserPreferences().getName(), userId);
    }

    private String generateWildcardKeyForProductPreferences(UUID userId) {
        return "%s::%s_%s_*".formatted(cacheConfig.getUserPreferences().getName(), userId, PRODUCTS);
    }

    private String generateWildcardKeyForCategoryPreferences(UUID userId) {
        return "%s::%s_%s_*".formatted(cacheConfig.getUserPreferences().getName(), userId, CATEGORIES);
    }
}
