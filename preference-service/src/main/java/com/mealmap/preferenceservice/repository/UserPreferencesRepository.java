package com.mealmap.preferenceservice.repository;

import com.mealmap.preferenceservice.entity.UserPreferences;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    Optional<UserPreferences> findByUserId(UUID userId);

    @Query("SELECT up FROM UserPreferences up " +
            "LEFT JOIN FETCH up.productPreferences pp " +
            "WHERE up.userId = :userId AND pp.preferenceType = :preferenceType")
    Optional<UserPreferences> findByUserIdAndProductPreferenceType(UUID userId, PreferenceType preferenceType);

    @Query("SELECT up FROM UserPreferences up " +
            "LEFT JOIN FETCH up.categoryPreferences cp " +
            "WHERE up.userId = :userId AND cp.preferenceType = :preferenceType")
    Optional<UserPreferences> findByUserIdAndCategoryPreferenceType(UUID userId, PreferenceType preferenceType);
}
