package com.mealmap.productservice.repository;

import com.mealmap.productservice.document.CategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDocumentRepository extends ElasticsearchRepository<CategoryDocument, Long> {
}
