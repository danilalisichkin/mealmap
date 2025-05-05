package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.category.CategoryCreatingDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.core.enums.sort.CategorySortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    PageDto<CategoryDto> getPageOfCategories(
            Integer offset, Integer limit, CategorySortField sortBy, Sort.Direction sortOrder, String search);

    List<CategoryDto> getCategories(Set<Long> ids);

    CategoryDto getCategory(Long id);

    CategoryTreeDto getCategoryTree(Long id);

    CategoryDto createCategory(CategoryCreatingDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryUpdatingDto categoryDto);

    void deleteCategory(Long id);
}
