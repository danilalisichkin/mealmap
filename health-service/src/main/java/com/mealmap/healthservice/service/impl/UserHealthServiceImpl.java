package com.mealmap.healthservice.service.impl;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.diet.UserDietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;
import com.mealmap.healthservice.core.mapper.UserDietMapper;
import com.mealmap.healthservice.core.mapper.UserPhysicHealthHistoryMapper;
import com.mealmap.healthservice.core.mapper.UserPhysicHealthMapper;
import com.mealmap.healthservice.entity.UserDiet;
import com.mealmap.healthservice.entity.UserPhysicHealth;
import com.mealmap.healthservice.entity.UserPhysicHealthHistory;
import com.mealmap.healthservice.exception.ResourceNotFoundException;
import com.mealmap.healthservice.repository.UserDietRepository;
import com.mealmap.healthservice.repository.UserPhysicHealthRepository;
import com.mealmap.healthservice.service.UserHealthService;
import com.mealmap.healthservice.validator.UserDietValidator;
import com.mealmap.healthservice.validator.UserPhysicHealthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_DIET_NOT_FOUND;
import static com.mealmap.healthservice.core.message.ApplicationMessages.USER_PHYSICAL_HEALTH_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserHealthServiceImpl implements UserHealthService {
    private final UserDietValidator userDietValidator;

    private final UserPhysicHealthValidator userPhysicHealthValidator;

    private final UserDietMapper userDietMapper;

    private final UserPhysicHealthMapper userPhysicHealthMapper;

    private final UserPhysicHealthHistoryMapper userPhysicHealthHistoryMapper;

    private final UserDietRepository userDietRepository;

    private final UserPhysicHealthRepository userPhysicHealthRepository;

    @Override
    public UserPhysicHealthDto getUserPhysicHealth(UUID userId) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);

        return userPhysicHealthMapper.entityToDto(userPhysicHealth);
    }

    @Override
    public List<UserPhysicHealthHistoryDto> getUserPhysicHealthHistory(UUID userId) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);

        List<UserPhysicHealthHistory> healthHistory = userPhysicHealth.getHistory();

        return userPhysicHealthHistoryMapper.entityListToDtoList(healthHistory);
    }

    @Override
    public UserDietDto getUserDiet(UUID userId) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);

        UserDiet diet = userPhysicHealth.getDiet();

        return userDietMapper.entityToDto(diet);
    }

    @Override
    @Transactional
    public UserPhysicHealthDto createUserPhysicHealth(UUID userId, UserPhysicHealthCreationDto userPhysicHealthDto) {
        userPhysicHealthValidator.validateUserIdUniqueness(userId);

        UserPhysicHealth userPhysicHealthToCreate = userPhysicHealthMapper.dtoToEntity(userPhysicHealthDto);
        userPhysicHealthToCreate.setUserId(userId);

        return userPhysicHealthMapper.entityToDto(
                userPhysicHealthRepository.save(userPhysicHealthToCreate));
    }

    @Override
    @Transactional
    public UserDietDto createUserDiet(UUID userId, UserDietCreationDto userDietDto) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);
        userDietValidator.validateDietUniqueness(userPhysicHealth);

        UserDiet dietToCreate = userDietMapper.dtoToEntity(userDietDto);
        dietToCreate.setPhysicHealth(userPhysicHealth);
        userPhysicHealth.setDiet(dietToCreate);

        createNewHealthHistory(userPhysicHealth);
        userPhysicHealthRepository.save(userPhysicHealth);

        return userDietMapper.entityToDto(
                userPhysicHealth.getDiet());
    }

    @Override
    @Transactional
    public UserPhysicHealthDto updateUserPhysicHealth(UUID userId, UserPhysicHealthUpdatingDto userPhysicHealthDto) {
        UserPhysicHealth userPhysicHealthToUpdate = getUserPhysicHealthEntity(userId);

        userPhysicHealthMapper.updateEntityFromDto(userPhysicHealthToUpdate, userPhysicHealthDto);

        createNewHealthHistory(userPhysicHealthToUpdate);

        return userPhysicHealthMapper.entityToDto(
                userPhysicHealthRepository.save(userPhysicHealthToUpdate));
    }

    @Override
    @Transactional
    public UserDietDto updateUserDiet(UUID userId, UserDietUpdatingDto userDietDto) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);
        userDietValidator.validateDietExistence(userPhysicHealth);

        userDietMapper.updateEntityFromDto(userPhysicHealth.getDiet(), userDietDto);
        userPhysicHealthRepository.save(userPhysicHealth);

        return userDietMapper.entityToDto(userPhysicHealth.getDiet());
    }

    @Override
    @Transactional
    public void deleteUserDiet(UUID userId) {
        UserPhysicHealth userPhysicHealth = getUserPhysicHealthEntity(userId);
        userDietValidator.validateDietExistence(userPhysicHealth);

        userDietRepository.delete(userPhysicHealth.getDiet());
        userPhysicHealth.setDiet(null);

        userPhysicHealthRepository.save(userPhysicHealth);
    }

    private UserPhysicHealth getUserPhysicHealthEntity(UUID userId) {
        return userPhysicHealthRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_PHYSICAL_HEALTH_NOT_FOUND.formatted(userId)));
    }

    private void createNewHealthHistory(UserPhysicHealth userPhysicHealth) {
        UserPhysicHealthHistory newHistory = UserPhysicHealthHistory.builder()
                .physicHealth(userPhysicHealth)
                .weight(userPhysicHealth.getWeight())
                .build();

        userPhysicHealth.getHistory().add(newHistory);
    }
}
