package com.mealmap.userservice.repository;

import com.mealmap.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository
        extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}
