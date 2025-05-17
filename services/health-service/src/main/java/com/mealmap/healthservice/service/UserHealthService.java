package com.mealmap.healthservice.service;

import com.mealmap.healthservice.core.dto.allergen.AllergenAddingDto;
import com.mealmap.healthservice.core.dto.allergen.AllergenDto;
import com.mealmap.healthservice.core.dto.diet.DietCreationDto;
import com.mealmap.healthservice.core.dto.diet.DietDto;
import com.mealmap.healthservice.core.dto.diet.DietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthUpdatingDto;

import java.util.List;
import java.util.UUID;

public interface UserHealthService {
    PhysicHealthDto getUserPhysicHealth(UUID userId);

    List<PhysicHealthHistoryDto> getUserPhysicHealthHistory(UUID userId);

    DietDto getUserDiet(UUID userId);

    List<AllergenDto> getUserAllergens(UUID userId);

    PhysicHealthDto createUserPhysicHealth(UUID userId, PhysicHealthCreationDto userPhysicHealthDto);

    AllergenDto addUserAllergen(UUID userId, AllergenAddingDto allergenDto);

    DietDto createUserDiet(UUID userId, DietCreationDto userDietDto);

    PhysicHealthDto updateUserPhysicHealth(UUID userId, PhysicHealthUpdatingDto userPhysicHealthDto);

    DietDto updateUserDiet(UUID userId, DietUpdatingDto userDietDto);

    void deleteUserDiet(UUID userId);

    void removeUserAllergen(UUID userId, Long allergenId);
}
