package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.client.ProductApiClient;
import com.mealmap.orderservice.client.dto.product.ProductDto;
import com.mealmap.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductApiClient productApiClient;

    @Override
    public List<ProductDto> getProducts(List<Long> ids) {
        return productApiClient.bulkGetProducts(ids);
    }
}
