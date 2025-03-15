package com.mealmap.preferenceservice.service.impl;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferenceDto;
import com.mealmap.preferenceservice.core.mapper.CategoryPreferenceMapper;
import com.mealmap.preferenceservice.core.mapper.ProductPreferenceMapper;
import com.mealmap.preferenceservice.core.mapper.UserPreferenceMapper;
import com.mealmap.preferenceservice.entity.CategoryPreference;
import com.mealmap.preferenceservice.entity.ProductPreference;
import com.mealmap.preferenceservice.entity.UserPreference;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import com.mealmap.preferenceservice.exception.ResourceNotFoundException;
import com.mealmap.preferenceservice.repository.UserPreferenceRepository;
import com.mealmap.preferenceservice.service.UserPreferenceRedisCacheService;
import com.mealmap.preferenceservice.service.UserPreferenceService;
import com.mealmap.preferenceservice.validator.UserPreferenceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.mealmap.preferenceservice.core.message.ApplicationMessages.PREFERENCES_FOR_USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "userPreferenceCacheResolver")
public class UserPreferenceServiceImpl implements UserPreferenceService {
    private final UserPreferenceRedisCacheService userPreferenceRedisService;

    private final UserPreferenceValidator userPreferenceValidator;

    private final UserPreferenceMapper userPreferenceMapper;

    private final ProductPreferenceMapper productPreferenceMapper;

    private final CategoryPreferenceMapper categoryPreferenceMapper;

    private final UserPreferenceRepository userPreferenceRepository;

    @Override
    @Cacheable(key = "#userId")
    public UserPreferenceDto getUserPreferences(UUID userId) {
        UserPreference userPreference = getUserPreferenceEntity(userId);

        return userPreferenceMapper.entityToDto(userPreference);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).PRODUCTS"
            + "+ '_' + #preferenceType")
    public List<ProductPreferenceDto> getProductPreferences(UUID userId, PreferenceType preferenceType) {
        UserPreference userPreference = preferenceType == null
                ? getUserPreferenceEntity(userId)
                : getUserPreferenceByProductPreferenceType(userId, preferenceType);

        List<ProductPreference> productPreferences = userPreference.getProductPreferences();

        return productPreferenceMapper.entityListToDtoList(productPreferences);
    }

    @Override
    @Cacheable(key = "#userId + '_' + T(com.mealmap.preferenceservice.cache.constant.CachePrefixes).CATEGORIES"
            + "+ '_' + #preferenceType")
    public List<CategoryPreferenceDto> getCategoryPreferences(UUID userId, PreferenceType preferenceType) {
        UserPreference userPreference = preferenceType == null
                ? getUserPreferenceEntity(userId)
                : getUserPreferenceByCategoryPreferenceType(userId, preferenceType);

        List<CategoryPreference> categoryPreferences = userPreference.getCategoryPreferences();

        return categoryPreferenceMapper.entityListToDtoList(categoryPreferences);
    }

    @Override
    @Transactional
    public ProductPreferenceDto addProductPreference(UUID userId, ProductPreferenceCreationDto productPreferenceDto) {
        UserPreference userPreferenceToUpdate = getUserPreferenceEntity(userId);
        List<ProductPreference> productPreferences = userPreferenceToUpdate.getProductPreferences();

        userPreferenceValidator.validateProductPreferenceUniqueness(
                productPreferences, productPreferenceDto.getProductId());

        ProductPreference productPreference = productPreferenceMapper.dtoToEntity(productPreferenceDto);
        productPreference.setUserPreference(userPreferenceToUpdate);
        productPreferences.add(productPreference);

        userPreferenceRepository.save(userPreferenceToUpdate);

        userPreferenceRedisService.updateUserPreferenceWithProductPreferences(userId, userPreferenceToUpdate);

        return productPreferenceMapper.entityToDto(
                productPreferences.getLast());
    }

    @Override
    @Transactional
    public CategoryPreferenceDto addCategoryPreference(UUID userId, CategoryPreferenceCreationDto categoryPreferenceDto) {
        UserPreference userPreferenceToUpdate = getUserPreferenceEntity(userId);
        List<CategoryPreference> categoryPreferences = userPreferenceToUpdate.getCategoryPreferences();

        userPreferenceValidator.validateCategoryPreferenceUniqueness(
                categoryPreferences, categoryPreferenceDto.getCategoryId());

        CategoryPreference categoryPreference = categoryPreferenceMapper.dtoToEntity(categoryPreferenceDto);
        categoryPreference.setUserPreference(userPreferenceToUpdate);
        categoryPreferences.add(categoryPreference);

        userPreferenceRepository.save(userPreferenceToUpdate);

        userPreferenceRedisService.updateUserPreferenceWithCategoryPreferences(userId, userPreferenceToUpdate);

        return categoryPreferenceMapper.entityToDto(
                categoryPreferences.getLast());
    }

    @Override
    @Transactional
    public void removeUserProductPreference(UUID userId, Long id) {
        UserPreference userPreferenceToUpdate = getUserPreferenceEntity(userId);
        List<ProductPreference> productPreferences = userPreferenceToUpdate.getProductPreferences();

        userPreferenceValidator.validateProductPreferenceExistence(productPreferences, id);

        productPreferences.removeIf(pp -> pp.getId().equals(id));

        userPreferenceRepository.save(userPreferenceToUpdate);

        userPreferenceRedisService.updateUserPreferenceWithProductPreferences(userId, userPreferenceToUpdate);
    }

    @Override
    @Transactional
    public void removeUserCategoryPreference(UUID userId, Long id) {
        UserPreference userPreferenceToUpdate = getUserPreferenceEntity(userId);
        List<CategoryPreference> categoryPreferences = userPreferenceToUpdate.getCategoryPreferences();

        userPreferenceValidator.validateCategoryPreferenceExistence(categoryPreferences, id);

        categoryPreferences.removeIf(cp -> cp.getId().equals(id));

        userPreferenceRedisService.updateUserPreferenceWithCategoryPreferences(userId, userPreferenceToUpdate);
    }

    private UserPreference getUserPreferenceEntity(UUID userId) {
        return userPreferenceRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }

    private UserPreference getUserPreferenceByProductPreferenceType(UUID userId, PreferenceType preferenceType) {
        return userPreferenceRepository
                .findByUserIdAndProductPreferenceType(userId, preferenceType)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }

    private UserPreference getUserPreferenceByCategoryPreferenceType(UUID userId, PreferenceType preferenceType) {
        return userPreferenceRepository
                .findByUserIdAndCategoryPreferenceType(userId, preferenceType)
                .orElseThrow(() -> new ResourceNotFoundException(PREFERENCES_FOR_USER_NOT_FOUND.formatted(userId)));
    }
}
