package com.mealmap.productservice.repository;

import com.mealmap.productservice.document.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc, Long> {
}
