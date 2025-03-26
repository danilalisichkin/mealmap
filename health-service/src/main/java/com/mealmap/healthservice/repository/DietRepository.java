package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.Diet;
import com.mealmap.healthservice.entity.PhysicHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
    boolean existsByPhysicHealth(PhysicHealth physicHealth);
}
