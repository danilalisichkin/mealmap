package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.core.dto.product.ProductCreationDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.core.enums.sort.ProductSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface ProductService {
    PageDto<ProductDto> getPageOfProducts(
            Integer offset, Integer limit, ProductSortField sortBy, Sort.Direction sortOrder,
            ProductFilter filter, String search);

    List<ProductDto> getProducts(Set<Long> ids);

    List<ProductDto> getAllProducts();

    ProductDto getProduct(Long id);

    ProductDto createProduct(ProductCreationDto productDto);

    ProductDto updateProduct(Long id, ProductUpdatingDto productDto);

    void deleteProduct(Long id);
}
