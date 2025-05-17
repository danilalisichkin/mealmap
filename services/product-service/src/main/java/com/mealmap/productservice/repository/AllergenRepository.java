package com.mealmap.productservice.repository;

import com.mealmap.productservice.entity.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, Long> {
    boolean existsByName(String name);
}
