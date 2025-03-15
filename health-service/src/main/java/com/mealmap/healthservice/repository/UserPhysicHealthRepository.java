package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.UserPhysicHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhysicHealthRepository extends JpaRepository<UserPhysicHealth, Long> {
}
