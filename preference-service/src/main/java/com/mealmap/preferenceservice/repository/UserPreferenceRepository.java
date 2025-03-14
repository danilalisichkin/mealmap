package com.mealmap.preferenceservice.repository;

import com.mealmap.preferenceservice.entity.UserPreference;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPreferenceRepository
        extends JpaRepository<UserPreference, Long>, JpaSpecificationExecutor<UserPreference> {

    Optional<UserPreference> findByUserId(UUID userId);

    @Query("SELECT up FROM UserPreference up " +
            "LEFT JOIN FETCH up.productPreferences pp " +
            "WHERE up.userId = :userId AND pp.preferenceType = :preferenceType")
    Optional<UserPreference> findByUserIdAndProductPreferenceType(UUID userId, PreferenceType preferenceType);

    @Query("SELECT up FROM UserPreference up " +
            "LEFT JOIN FETCH up.categoryPreferences cp " +
            "WHERE up.userId = :userId AND cp.preferenceType = :preferenceType")
    Optional<UserPreference> findByUserIdAndCategoryPreferenceType(UUID userId, PreferenceType preferenceType);
}
