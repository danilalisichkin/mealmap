package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.UserPhysicHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPhysicHealthRepository extends JpaRepository<UserPhysicHealth, Long> {
    Optional<UserPhysicHealth> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);
}
