package com.mealmap.healthservice.service;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.diet.UserDietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;

import java.util.List;
import java.util.UUID;

public interface UserHealthService {
    UserPhysicHealthDto getUserPhysicHealth(UUID userId);

    List<UserPhysicHealthHistoryDto> getUserPhysicHealthHistory(UUID userId);

    UserDietDto getUserDiet(UUID userId);

    UserPhysicHealthDto createUserPhysicHealth(UUID userId, UserPhysicHealthCreationDto userPhysicHealthDto);

    UserDietDto createUserDiet(UUID userId, UserDietCreationDto userDietDto);

    UserPhysicHealthDto updateUserPhysicHealth(UUID userId, UserPhysicHealthUpdatingDto userPhysicHealthDto);

    UserDietDto updateUserDiet(UUID userId, UserDietUpdatingDto userDietDto);

    void deleteUserDiet(UUID userId);
}
