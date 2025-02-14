package com.mealmap.productservice.repository;

import com.mealmap.productservice.document.NutrientDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientDocumentRepository extends ElasticsearchRepository<NutrientDocument, Long> {
}
