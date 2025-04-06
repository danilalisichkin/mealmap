package com.mealmap.productservice.repository;

import com.mealmap.productservice.entity.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}
