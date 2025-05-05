package com.mealmap.preferenceservice.repository;

import com.mealmap.preferenceservice.entity.CategoryPreference;
import com.mealmap.preferenceservice.entity.ProductPreference;
import com.mealmap.preferenceservice.entity.UserPreferences;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    Optional<UserPreferences> findByUserId(UUID userId);

    @Query("""
            SELECT up FROM UserPreferences up
                        LEFT JOIN FETCH up.productPreferences pp
                        WHERE up.userId = :userId AND pp.preferenceType = :preferenceType""")
    Optional<UserPreferences> findByUserIdAndProductPreferenceType(UUID userId, PreferenceType preferenceType);

    @Query("""
            SELECT up FROM UserPreferences up
                        LEFT JOIN FETCH up.categoryPreferences cp
                        WHERE up.userId = :userId AND cp.preferenceType = :preferenceType""")
    Optional<UserPreferences> findByUserIdAndCategoryPreferenceType(UUID userId, PreferenceType preferenceType);

    @Query("""
            SELECT pp FROM ProductPreference pp
            WHERE pp.userPreferences.userId = :userId AND pp.productId = :productId""")
    Optional<ProductPreference> findProductPreferenceByUserIdAndProductId(UUID userId, Long productId);

    @Query("""
            SELECT cp FROM CategoryPreference cp
            WHERE cp.userPreferences.userId = :userId AND cp.categoryId = :categoryId""")
    Optional<CategoryPreference> findCategoryPreferenceByUserIdAndCategoryId(UUID userId, Long categoryId);

    @Query("""
                SELECT COUNT(pp) > 0 FROM ProductPreference pp
                WHERE pp.userPreferences.userId = :userId AND pp.productId = :productId
            """)
    boolean existsProductPreferenceByUserIdAndProductId(UUID userId, Long productId);

    @Query("""
                SELECT COUNT(cp) > 0 FROM CategoryPreference cp
                WHERE cp.userPreferences.userId = :userId AND cp.categoryId = :categoryId
            """)
    boolean existsCategoryPreferenceByUserIdAndCategoryId(UUID userId, Long categoryId);
}
