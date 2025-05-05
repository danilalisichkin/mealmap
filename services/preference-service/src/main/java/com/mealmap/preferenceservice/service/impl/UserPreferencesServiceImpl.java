package com.mealmap.preferenceservice.service.impl;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferencesDto;
import com.mealmap.preferenceservice.core.mapper.CategoryPreferenceMapper;
import com.mealmap.preferenceservice.core.mapper.ProductPreferenceMapper;
import com.mealmap.preferenceservice.core.mapper.UserPreferencesMapper;
import com.mealmap.preferenceservice.entity.CategoryPreference;
import com.mealmap.preferenceservice.entity.ProductPreference;
import com.mealmap.preferenceservice.entity.UserPreferences;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import com.mealmap.preferenceservice.repository.UserPreferencesRepository;
import com.mealmap.preferenceservice.service.UserPreferencesRedisCacheService;
import com.mealmap.preferenceservice.service.UserPreferencesService;
import com.mealmap.preferenceservice.validator.UserPreferencesValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.mealmap.preferenceservice.core.message.ApplicationMessages.CATEGORY_PREFERENCE_NOT_FOUND;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PREFERENCES_FOR_USER_NOT_FOUND;
import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PRODUCT_PREFERENCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "userPreferenceCacheResolver")
public class UserPreferencesServiceImpl implements UserPreferencesService {
    private final UserPreferencesRedisCacheService userPreferencesRedisService;

    private final UserPreferencesValidator userPreferencesValidator;

    private final UserPreferencesMapper userPreferencesMapper;

    private final ProductPreferenceMapper productPreferenceMapper;

    private final CategoryPreferenceMapper categoryPreferenceMapper;

    private final UserPreferencesRepository userPreferencesRepository;

    @Override
    @Cacheable(key = "#userId")
    public UserPreferencesDto getAllPreferences(UUID userId) {
        UserPreferences userPreferences = getUserPreferenceEntity(userId);

        return userPreferencesMapper.entityToDto(userPreferences);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).PRODUCTS"
            + "+ '_' + #preferenceType")
    public List<ProductPreferenceDto> getProductPreferences(UUID userId, PreferenceType preferenceType) {
        UserPreferences userPreferences = preferenceType == null
                ? getUserPreferenceEntity(userId)
                : getUserPreferenceByProductPreferenceType(userId, preferenceType);

        List<ProductPreference> productPreferences = userPreferences.getProductPreferences();

        return productPreferenceMapper.entityListToDtoList(productPreferences);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).CATEGORIES"
            + "+ '_' + #preferenceType")
    public List<CategoryPreferenceDto> getCategoryPreferences(UUID userId, PreferenceType preferenceType) {
        UserPreferences userPreferences = preferenceType == null
                ? getUserPreferenceEntity(userId)
                : getUserPreferenceByCategoryPreferenceType(userId, preferenceType);

        List<CategoryPreference> categoryPreferences = userPreferences.getCategoryPreferences();

        return categoryPreferenceMapper.entityListToDtoList(categoryPreferences);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).PRODUCTS" +
            "+ '_product:' + #productId")
    public ProductPreferenceDto getProductPreference(UUID userId, Long productId) {
        ProductPreference productPreference = userPreferencesRepository
                .findProductPreferenceByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_PREFERENCE_NOT_FOUND.formatted(productId)));

        return productPreferenceMapper.entityToDto(productPreference);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).CATEGORIES" +
            "+ '_category:' + #categoryId")
    public CategoryPreferenceDto getCategoryPreference(UUID userId, Long categoryId) {
        CategoryPreference categoryPreference = userPreferencesRepository
                .findCategoryPreferenceByUserIdAndCategoryId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_PREFERENCE_NOT_FOUND.formatted(categoryId)));

        return categoryPreferenceMapper.entityToDto(categoryPreference);
    }

    @Override
    @Transactional
    public ProductPreferenceDto addProductPreference(UUID userId, ProductPreferenceCreationDto productPreferenceDto) {
        UserPreferences userPreferencesToUpdate = getUserPreferenceEntity(userId);
        List<ProductPreference> productPreferences = userPreferencesToUpdate.getProductPreferences();

        userPreferencesValidator.validateProductPreferenceUniqueness(
                productPreferences, productPreferenceDto.getProductId());

        ProductPreference productPreference = productPreferenceMapper.dtoToEntity(productPreferenceDto);
        productPreference.setUserPreferences(userPreferencesToUpdate);
        productPreferences.add(productPreference);

        userPreferencesRepository.save(userPreferencesToUpdate);

        userPreferencesRedisService.updatePreferenceWithProductPreferences(userId, userPreferencesToUpdate);

        return productPreferenceMapper.entityToDto(
                productPreferences.getLast());
    }

    @Override
    @Transactional
    public CategoryPreferenceDto addCategoryPreference(UUID userId, CategoryPreferenceCreationDto categoryPreferenceDto) {
        UserPreferences userPreferencesToUpdate = getUserPreferenceEntity(userId);
        List<CategoryPreference> categoryPreferences = userPreferencesToUpdate.getCategoryPreferences();

        userPreferencesValidator.validateCategoryPreferenceUniqueness(
                categoryPreferences, categoryPreferenceDto.getCategoryId());

        CategoryPreference categoryPreference = categoryPreferenceMapper.dtoToEntity(categoryPreferenceDto);
        categoryPreference.setUserPreferences(userPreferencesToUpdate);
        categoryPreferences.add(categoryPreference);

        userPreferencesRepository.save(userPreferencesToUpdate);

        userPreferencesRedisService.updatePreferenceWithCategoryPreferences(userId, userPreferencesToUpdate);

        return categoryPreferenceMapper.entityToDto(
                categoryPreferences.getLast());
    }

    @Override
    @Transactional
    public void removeProductPreference(UUID userId, Long productId) {
        UserPreferences userPreferencesToUpdate = getUserPreferenceEntity(userId);
        List<ProductPreference> productPreferences = userPreferencesToUpdate.getProductPreferences();

        userPreferencesValidator.validateProductPreferenceExistence(productPreferences, productId);

        productPreferences.removeIf(pp -> pp.getProductId().equals(productId));

        userPreferencesRepository.save(userPreferencesToUpdate);

        userPreferencesRedisService.updatePreferenceWithProductPreferences(userId, userPreferencesToUpdate);
    }

    @Override
    @Transactional
    public void removeCategoryPreference(UUID userId, Long categoryId) {
        UserPreferences userPreferencesToUpdate = getUserPreferenceEntity(userId);
        List<CategoryPreference> categoryPreferences = userPreferencesToUpdate.getCategoryPreferences();

        userPreferencesValidator.validateCategoryPreferenceExistence(categoryPreferences, categoryId);

        categoryPreferences.removeIf(cp -> cp.getCategoryId().equals(categoryId));

        userPreferencesRedisService.updatePreferenceWithCategoryPreferences(userId, userPreferencesToUpdate);
    }

    private UserPreferences getUserPreferenceEntity(UUID userId) {
        return userPreferencesRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }

    private UserPreferences getUserPreferenceByProductPreferenceType(UUID userId, PreferenceType preferenceType) {
        return userPreferencesRepository
                .findByUserIdAndProductPreferenceType(userId, preferenceType)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }

    private UserPreferences getUserPreferenceByCategoryPreferenceType(UUID userId, PreferenceType preferenceType) {
        return userPreferencesRepository
                .findByUserIdAndCategoryPreferenceType(userId, preferenceType)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }
}
