package com.mealmap.cartservice.repository;

import com.mealmap.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    Long findIdByUserId(UUID userId);
}
