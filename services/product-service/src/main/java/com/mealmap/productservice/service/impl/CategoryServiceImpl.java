package com.mealmap.productservice.service.impl;

import com.mealmap.productservice.core.dto.category.CategoryCreationDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.core.mapper.CategoryMapper;
import com.mealmap.productservice.entity.Category;
import com.mealmap.productservice.repository.CategoryRepository;
import com.mealmap.productservice.service.CategoryService;
import com.mealmap.productservice.validator.CategoryValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.mealmap.productservice.core.message.ApplicationMessages.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "categoryCacheResolver")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryValidator categoryValidator;

    private final CategoryMapper categoryMapper;


    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.entityListToDtoList(categories);
    }

    @Override
    @Cacheable(key = "#ids.stream().sorted().toList().toString()")
    public List<CategoryDto> getCategories(Set<Long> ids) {
        return categoryMapper.entityListToDtoList(
                categoryRepository.findAllById(ids));
    }

    @Override
    @Cacheable(key = "#id")
    public CategoryDto getCategory(Long id) {
        Category category = getCategoryEntity(id);

        return categoryMapper.entityToDto(category);
    }

    @Override
    public CategoryTreeDto getCategoryTree(Long id) {
        Category category = getCategoryEntity(id);

        return categoryMapper.entityToTreeDto(category);
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public CategoryDto createCategory(CategoryCreationDto categoryDto) {
        categoryValidator.validateNameUniqueness(categoryDto.getName());

        Category categoryToCreate = categoryMapper.dtoToEntity(categoryDto);
        setParentCategory(categoryToCreate, categoryDto.getParent());

        return categoryMapper.entityToDto(
                categoryRepository.save(categoryToCreate));
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public CategoryDto updateCategory(Long id, CategoryUpdatingDto categoryDto) {
        Category categoryToUpdate = getCategoryEntity(id);

        if (!categoryToUpdate.getName().equals(categoryDto.getName())) {
            categoryValidator.validateNameUniqueness(categoryDto.getName());
        }

        categoryMapper.updateEntityFromDto(categoryToUpdate, categoryDto);
        updateParentCategory(categoryToUpdate, categoryDto.getParent());

        return categoryMapper.entityToDto(
                categoryRepository.save(categoryToUpdate));
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public void deleteCategory(Long id) {
        categoryValidator.validateExistenceOfCategoryWithId(id);

        categoryRepository.deleteById(id);
    }

    private void setParentCategory(Category category, Long parentCategoryId) {
        if (parentCategoryId != null) {
            Category parentCategory = getCategoryEntity(parentCategoryId);
            category.setParent(parentCategory);
        }
    }

    private void updateParentCategory(Category categoryToUpdate, Long newParentId) {
        Category currentParent = categoryToUpdate.getParent();

        if (newParentId == null) {
            categoryToUpdate.setParent(null);
        } else if (currentParent == null || !currentParent.getId().equals(newParentId)) {
            Category newParrentCategory = getCategoryEntity(newParentId);
            categoryToUpdate.setParent(newParrentCategory);
        }
    }

    private Category getCategoryEntity(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND.formatted(id)));
    }
}
