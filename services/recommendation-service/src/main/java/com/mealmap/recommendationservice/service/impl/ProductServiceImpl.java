package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.client.ProductApiClient;
import com.mealmap.recommendationservice.core.model.product.Product;
import com.mealmap.recommendationservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductApiClient productApiClient;

    @Override
    public List<Product> getAllProducts() {
        return productApiClient.getAllProducts();
    }
}
