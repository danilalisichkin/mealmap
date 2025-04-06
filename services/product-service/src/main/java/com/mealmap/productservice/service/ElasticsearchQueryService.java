package com.mealmap.productservice.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.mealmap.productservice.core.dto.filter.ProductFilter;
import org.springframework.data.domain.Pageable;

public interface ElasticsearchQueryService {
    Query buildQueryForProducts(Pageable pageable, ProductFilter filter, String search);

    Query buildQueryForCategories(Pageable pageable, String search);
}
