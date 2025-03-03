package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.filter.ProductFilterDto;
import com.mealmap.productservice.core.dto.page.PageDto;
import com.mealmap.productservice.core.dto.product.ProductCreatingDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.core.enums.sort.ProductSortField;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    PageDto<ProductDto> getPageOfProducts(
            Integer offset, Integer limit, ProductSortField sortBy, Sort.Direction sortOrder,
            ProductFilterDto filter, String search);

    List<ProductDto> getProducts(List<Long> ids);

    ProductDto getProduct(Long id);

    ProductDto createProduct(ProductCreatingDto productDto);

    ProductDto updateProduct(Long id, ProductUpdatingDto productDto);

    void deleteProduct(Long id);
}
