package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.client.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
}
