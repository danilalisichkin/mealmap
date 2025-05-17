package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.category.CategoryCreationDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategories(Set<Long> ids);

    CategoryDto getCategory(Long id);

    CategoryTreeDto getCategoryTree(Long id);

    CategoryDto createCategory(CategoryCreationDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryUpdatingDto categoryDto);

    void deleteCategory(Long id);
}
