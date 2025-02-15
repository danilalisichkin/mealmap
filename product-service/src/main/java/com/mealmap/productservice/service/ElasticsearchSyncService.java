package com.mealmap.productservice.service;

public interface ElasticsearchSyncService {
    void syncProducts();

    void syncCategories();
}
