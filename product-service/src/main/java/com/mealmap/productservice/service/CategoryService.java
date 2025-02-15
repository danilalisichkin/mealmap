package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.category.CategoryCreatingDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.core.dto.page.PageDto;
import com.mealmap.productservice.core.enums.sort.CategorySortField;
import org.springframework.data.domain.Sort;

public interface CategoryService {
    PageDto<CategoryDto> getPageOfCategories(
            Integer offset, Integer limit, CategorySortField sortBy, Sort.Direction sortOrder, String search);

    CategoryDto getCategory(Long id);

    CategoryDto createCategory(CategoryCreatingDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryUpdatingDto categoryDto);

    void deleteCategory(Long id);
}
