package com.mealmap.productservice.service.impl;

import com.mealmap.productservice.core.mapper.CategoryMapper;
import com.mealmap.productservice.core.mapper.ProductMapper;
import com.mealmap.productservice.repository.CategoryDocRepository;
import com.mealmap.productservice.repository.CategoryRepository;
import com.mealmap.productservice.repository.ProductDocRepository;
import com.mealmap.productservice.repository.ProductRepository;
import com.mealmap.productservice.service.ElasticsearchSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElasticsearchSyncServiceImpl implements ElasticsearchSyncService {
    private final ProductRepository productRepository;

    private final ProductDocRepository productDocRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    private final CategoryDocRepository categoryDocRepository;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    @Scheduled(fixedRateString = "${business.elastic.products.sync-interval}000")
    public void syncProducts() {
        log.info("Start sync products");

        productDocRepository.deleteAll();
        productDocRepository.saveAll(
                productRepository.findAll().stream()
                        .map(productMapper::entityToDocument)
                        .toList());

        log.info("Finish sync products");
    }

    @Override
    @Transactional(readOnly = true)
    @Scheduled(fixedRateString = "${business.elastic.categories.sync-interval}000")
    public void syncCategories() {
        log.info("Start sync categories");

        categoryDocRepository.deleteAll();
        categoryDocRepository.saveAll(
                categoryRepository.findAll().stream()
                        .map(categoryMapper::entityToDocument)
                        .toList());

        log.info("Finish sync categories");
    }
}
