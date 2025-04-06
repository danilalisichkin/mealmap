package com.mealmap.productservice.repository;

import com.mealmap.productservice.document.CategoryDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDocRepository extends ElasticsearchRepository<CategoryDoc, Long> {
}
