package com.mealmap.organizationservice.cache.resolver;

import com.mealmap.organizationservice.cache.properties.CacheProperties;
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
public class OrganizationCacheResolver implements CacheResolver {
    private final CacheManager cacheManager;

    private final CacheProperties cacheProperties;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Cache cache = cacheManager.getCache(cacheProperties.getOrganization().getName());

        return cache != null ? List.of(cache) : Collections.emptyList();
    }
}
