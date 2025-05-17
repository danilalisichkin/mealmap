package com.mealmap.productservice.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.core.dto.product.ProductCreationDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.core.enums.sort.ProductSortField;
import com.mealmap.productservice.core.mapper.ProductMapper;
import com.mealmap.productservice.document.ProductDoc;
import com.mealmap.productservice.entity.Allergen;
import com.mealmap.productservice.entity.Category;
import com.mealmap.productservice.entity.Product;
import com.mealmap.productservice.repository.AllergenRepository;
import com.mealmap.productservice.repository.CategoryRepository;
import com.mealmap.productservice.repository.ProductRepository;
import com.mealmap.productservice.service.ElasticsearchQueryService;
import com.mealmap.productservice.service.ProductService;
import com.mealmap.productservice.util.ElasticsearchPageBuilder;
import com.mealmap.productservice.validator.ProductValidator;
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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.mealmap.productservice.core.message.ApplicationMessages.ALLERGENS_NOT_FOUND;
import static com.mealmap.productservice.core.message.ApplicationMessages.CATEGORIES_NOT_FOUND;
import static com.mealmap.productservice.core.message.ApplicationMessages.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "productCacheResolver")
public class ProductServiceImpl implements ProductService {
    private final ElasticsearchQueryService esQueryService;

    private final ElasticsearchClient esClient;

    private final ProductValidator productValidator;

    private final ProductMapper productMapper;

    private final PageMapper pageMapper;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final AllergenRepository allergenRepository;

    @Override
    @Cacheable
    @SneakyThrows
    public PageDto<ProductDto> getPageOfProducts(
            Integer offset, Integer limit, ProductSortField sortBy, Sort.Direction sortOrder,
            ProductFilter filter, String search) {

        PageRequest pageRequest = PageBuilder.buildPageRequest(
                offset, limit, sortBy.getValue(), sortOrder);

        Query searchQuery = esQueryService.buildQueryForProducts(pageRequest, filter, search);

        SortOptions sortOptions = esQueryService.buildSortOptions(sortBy.getValue(), sortOrder);

        SearchRequest searchRequest = SearchRequest.of(sr -> sr
                .index("products")
                .query(searchQuery)
                .from(offset * limit)
                .size(limit)
                .sort(sortOptions));

        SearchResponse<ProductDoc> response = esClient.search(searchRequest, ProductDoc.class);

        return pageMapper.pageToPageDto(
                productMapper.docPageToDtoPage(
                        ElasticsearchPageBuilder.buildPage(response, pageRequest)));
    }

    @Override
    @Cacheable(key = "#ids.stream().sorted().toList().toString()")
    public List<ProductDto> getProducts(Set<Long> ids) {
        return productMapper.entityListToDtoList(
                productRepository.findAllById(ids));
    }

    @Override
    @Cacheable(key = "'menu'")
    public List<ProductDto> getAllProducts() {
        return productMapper.entityListToDtoList(
                productRepository.findAll());
    }

    @Override
    @Cacheable(key = "#id")
    public ProductDto getProduct(Long id) {
        Product product = getProductEntity(id);

        return productMapper.entityToDto(product);
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public ProductDto createProduct(ProductCreationDto productDto) {
        productValidator.validateNameUniqueness(productDto.getName());

        List<Category> categories = getCategoryEntities(productDto.getCategories());
        List<Allergen> allergens = getAllergenEntities(productDto.getAllergens());

        Product productToCreate = productMapper.dtoToEntity(productDto);

        productToCreate.setCategories(categories);
        productToCreate.setAllergens(allergens);

        return productMapper.entityToDto(
                productRepository.save(productToCreate));
    }

    @Override
    @Transactional
    @CachePut(key = "#id")
    public ProductDto updateProduct(Long id, ProductUpdatingDto productDto) {
        Product productToUpdate = getProductEntity(id);

        if (!productToUpdate.getName().equals(productDto.getName())) {
            productValidator.validateNameUniqueness(productDto.getName());
        }

        productMapper.updateEntityFromDto(productToUpdate, productDto);

        updateCategories(productToUpdate, productDto.getCategories());
        updateAllergens(productToUpdate, productDto.getAllergens());

        return productMapper.entityToDto(
                productRepository.save(productToUpdate));
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public void deleteProduct(Long id) {
        productValidator.validateExistenceOfProductWithId(id);

        productRepository.deleteById(id);
    }

    private Product getProductEntity(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND.formatted(id)));
    }

    private List<Category> getCategoryEntities(Collection<Long> ids) {
        List<Category> categories = categoryRepository.findAllById(ids);

        if (categories.size() < ids.size()) {
            List<Long> missingIds = ids.stream()
                    .filter(id -> categories.stream()
                            .noneMatch(category -> category
                                    .getId()
                                    .equals(id)))
                    .toList();

            throw new ResourceNotFoundException(CATEGORIES_NOT_FOUND.formatted(missingIds.toString()));
        }

        return categories;
    }

    private List<Allergen> getAllergenEntities(Collection<Long> ids) {
        List<Allergen> allergens = allergenRepository.findAllById(ids);

        if (allergens.size() < ids.size()) {
            List<Long> missingIds = ids.stream()
                    .filter(id -> allergens.stream()
                            .noneMatch(category -> category
                                    .getId()
                                    .equals(id)))
                    .toList();

            throw new ResourceNotFoundException(ALLERGENS_NOT_FOUND.formatted(missingIds.toString()));
        }

        return allergens;
    }

    private void updateCategories(Product productToUpdate, Collection<Long> newCategoryIds) {
        if (newCategoryIds == null || newCategoryIds.isEmpty()) {
            productToUpdate.setCategories(null);
        } else {
            productToUpdate.setCategories(
                    getCategoryEntities(newCategoryIds));
        }
    }

    private void updateAllergens(Product productToUpdate, Collection<Long> newAllergenIds) {
        if (newAllergenIds == null || newAllergenIds.isEmpty()) {
            productToUpdate.setAllergens(null);
        } else {
            productToUpdate.setAllergens(
                    getAllergenEntities(newAllergenIds));
        }
    }
}
