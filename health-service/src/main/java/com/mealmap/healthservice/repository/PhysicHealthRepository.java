package com.mealmap.healthservice.repository;

import com.mealmap.healthservice.entity.PhysicHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhysicHealthRepository extends JpaRepository<PhysicHealth, Long> {
    Optional<PhysicHealth> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);
}
