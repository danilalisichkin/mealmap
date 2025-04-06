package com.mealmap.userservice.cache.resolver;

import com.mealmap.userservice.cache.config.CacheConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProfileCacheResolver implements CacheResolver {
    private final CacheManager cacheManager;

    private final CacheConfig cacheConfig;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Cache cache = cacheManager.getCache(cacheConfig.getUserProfile().getName());

        return cache != null ? List.of(cache) : Collections.emptyList();
    }
}
