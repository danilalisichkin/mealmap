package com.mealmap.productservice.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mealmap.productservice.core.dto.category.CategoryCreatingDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.core.enums.sort.CategorySortField;
import com.mealmap.productservice.core.mapper.CategoryMapper;
import com.mealmap.productservice.document.CategoryDoc;
import com.mealmap.productservice.entity.Category;
import com.mealmap.productservice.repository.CategoryRepository;
import com.mealmap.productservice.service.CategoryService;
import com.mealmap.productservice.service.ElasticsearchQueryService;
import com.mealmap.productservice.util.ElasticsearchPageBuilder;
import com.mealmap.productservice.validator.CategoryValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.productservice.core.message.ApplicationMessages.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "categoryCacheResolver")
public class CategoryServiceImpl implements CategoryService {
    private final ElasticsearchQueryService esQueryService;

    private final ElasticsearchClient esClient;

    private final CategoryValidator categoryValidator;

    private final CategoryMapper categoryMapper;

    private final PageMapper pageMapper;

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable
    @SneakyThrows
    public PageDto<CategoryDto> getPageOfCategories(
            Integer offset, Integer limit, CategorySortField sortBy, Sort.Direction sortOrder, String search) {

        PageRequest pageRequest = PageBuilder.buildPageRequest(
                offset, limit, sortBy.getValue(), sortOrder);

        Query searchQuery = esQueryService.buildQueryForCategories(pageRequest, search);
        SearchRequest searchRequest = SearchRequest.of(sr -> sr
                .query(searchQuery)
                .index("categories"));

        SearchResponse<CategoryDoc> response = esClient.search(searchRequest, CategoryDoc.class);

        return pageMapper.pageToPageDto(
                categoryMapper.docPageToDtoPage(
                        ElasticsearchPageBuilder.buildPage(response, pageRequest)));
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
    public CategoryDto createCategory(CategoryCreatingDto categoryDto) {
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
