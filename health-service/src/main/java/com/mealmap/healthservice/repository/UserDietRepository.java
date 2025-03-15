package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.UserDiet;
import com.mealmap.healthservice.entity.UserPhysicHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDietRepository extends JpaRepository<UserDiet, Long> {
    boolean existsByPhysicHealth(UserPhysicHealth physicHealth);
}
