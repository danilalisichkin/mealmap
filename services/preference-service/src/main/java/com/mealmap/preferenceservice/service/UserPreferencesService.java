package com.mealmap.preferenceservice.service;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferencesDto;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;

import java.util.List;
import java.util.UUID;

public interface UserPreferencesService {
    UserPreferencesDto getAllPreferences(UUID userId);

    List<ProductPreferenceDto> getProductPreferences(UUID userId, PreferenceType preferenceType);

    List<CategoryPreferenceDto> getCategoryPreferences(UUID userId, PreferenceType preferenceType);

    ProductPreferenceDto getProductPreference(UUID userId, Long productId);

    CategoryPreferenceDto getCategoryPreference(UUID userId, Long categoryId);

    ProductPreferenceDto addProductPreference(UUID userId, ProductPreferenceCreationDto productPreferenceDto);

    CategoryPreferenceDto addCategoryPreference(UUID userId, CategoryPreferenceCreationDto categoryPreferenceDto);

    void removeProductPreference(UUID userId, Long productId);

    void removeCategoryPreference(UUID userId, Long categoryId);
}
