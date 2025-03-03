package com.mealmap.orderservice.service;

import com.mealmap.orderservice.client.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts(List<Long> ids);
}
