package com.mealmap.productservice.repository;

import com.mealmap.productservice.entity.NutrientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientEntityRepository extends JpaRepository<NutrientEntity, Long> {
}
