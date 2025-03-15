package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.UserDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDietRepository extends JpaRepository<UserDiet, Long> {
}
