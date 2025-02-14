package com.mealmap.productservice.repository;

import com.mealmap.productservice.document.NutrientDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientDocRepository extends ElasticsearchRepository<NutrientDoc, Long> {
}
