package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.model.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
}
