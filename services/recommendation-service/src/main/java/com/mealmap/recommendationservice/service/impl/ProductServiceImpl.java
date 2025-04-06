package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.client.ProductApiClient;
import com.mealmap.recommendationservice.client.dto.product.ProductDto;
import com.mealmap.recommendationservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductApiClient productApiClient;

    @Override
    public List<ProductDto> getAllProducts() {
        return productApiClient.getAllProducts();
    }
}
