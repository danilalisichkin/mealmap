package com.mealmap.healthservice.service.impl;

import com.mealmap.healthservice.core.dto.diet.DietCreationDto;
import com.mealmap.healthservice.core.dto.diet.DietDto;
import com.mealmap.healthservice.core.dto.diet.DietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthUpdatingDto;
import com.mealmap.healthservice.core.mapper.DietMapper;
import com.mealmap.healthservice.core.mapper.PhysicHealthHistoryMapper;
import com.mealmap.healthservice.core.mapper.PhysicHealthMapper;
import com.mealmap.healthservice.entity.Diet;
import com.mealmap.healthservice.entity.PhysicHealth;
import com.mealmap.healthservice.entity.PhysicHealthHistory;
import com.mealmap.healthservice.repository.DietRepository;
import com.mealmap.healthservice.repository.PhysicHealthRepository;
import com.mealmap.healthservice.service.UserHealthService;
import com.mealmap.healthservice.validator.DietValidator;
import com.mealmap.healthservice.validator.PhysicHealthValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
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
    private final DietValidator dietValidator;

    private final PhysicHealthValidator physicHealthValidator;

    private final DietMapper dietMapper;

    private final PhysicHealthMapper physicHealthMapper;

    private final PhysicHealthHistoryMapper physicHealthHistoryMapper;

    private final DietRepository dietRepository;

    private final PhysicHealthRepository physicHealthRepository;

    @Override
    public PhysicHealthDto getUserPhysicHealth(UUID userId) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);

        return physicHealthMapper.entityToDto(physicHealth);
    }

    @Override
    public List<PhysicHealthHistoryDto> getUserPhysicHealthHistory(UUID userId) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);

        List<PhysicHealthHistory> healthHistory = physicHealth.getHistory();

        return physicHealthHistoryMapper.entityListToDtoList(healthHistory);
    }

    @Override
    public DietDto getUserDiet(UUID userId) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);

        Diet diet = physicHealth.getDiet();
        if (diet == null) {
            throw new ResourceNotFoundException(USER_DIET_NOT_FOUND.formatted(userId));
        }

        return dietMapper.entityToDto(diet);
    }

    @Override
    @Transactional
    public PhysicHealthDto createUserPhysicHealth(UUID userId, PhysicHealthCreationDto userPhysicHealthDto) {
        physicHealthValidator.validateUserIdUniqueness(userId);

        PhysicHealth physicHealthToCreate = physicHealthMapper.dtoToEntity(userPhysicHealthDto);
        physicHealthToCreate.setUserId(userId);

        return physicHealthMapper.entityToDto(
                physicHealthRepository.save(physicHealthToCreate));
    }

    @Override
    @Transactional
    public DietDto createUserDiet(UUID userId, DietCreationDto userDietDto) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);
        dietValidator.validateDietUniqueness(physicHealth);

        Diet dietToCreate = dietMapper.dtoToEntity(userDietDto);
        dietToCreate.setPhysicHealth(physicHealth);
        physicHealth.setDiet(dietToCreate);

        createNewHealthHistory(physicHealth);
        physicHealthRepository.save(physicHealth);

        return dietMapper.entityToDto(
                physicHealth.getDiet());
    }

    @Override
    @Transactional
    public PhysicHealthDto updateUserPhysicHealth(UUID userId, PhysicHealthUpdatingDto userPhysicHealthDto) {
        PhysicHealth physicHealthToUpdate = getUserPhysicHealthEntity(userId);

        physicHealthMapper.updateEntityFromDto(physicHealthToUpdate, userPhysicHealthDto);

        createNewHealthHistory(physicHealthToUpdate);

        return physicHealthMapper.entityToDto(
                physicHealthRepository.save(physicHealthToUpdate));
    }

    @Override
    @Transactional
    public DietDto updateUserDiet(UUID userId, DietUpdatingDto userDietDto) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);
        dietValidator.validateDietExistence(physicHealth);

        dietMapper.updateEntityFromDto(physicHealth.getDiet(), userDietDto);
        physicHealthRepository.save(physicHealth);

        return dietMapper.entityToDto(physicHealth.getDiet());
    }

    @Override
    @Transactional
    public void deleteUserDiet(UUID userId) {
        PhysicHealth physicHealth = getUserPhysicHealthEntity(userId);
        dietValidator.validateDietExistence(physicHealth);

        dietRepository.delete(physicHealth.getDiet());
        physicHealth.setDiet(null);

        physicHealthRepository.save(physicHealth);
    }

    private PhysicHealth getUserPhysicHealthEntity(UUID userId) {
        return physicHealthRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_PHYSICAL_HEALTH_NOT_FOUND.formatted(userId)));
    }

    private void createNewHealthHistory(PhysicHealth physicHealth) {
        PhysicHealthHistory newHistory = PhysicHealthHistory.builder()
                .physicHealth(physicHealth)
                .weight(physicHealth.getWeight())
                .build();

        physicHealth.getHistory().add(newHistory);
    }
}
