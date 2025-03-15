package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.UserPhysicHealthHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhysicHealthHistoryRepository extends JpaRepository<UserPhysicHealthHistory, Long> {
}
