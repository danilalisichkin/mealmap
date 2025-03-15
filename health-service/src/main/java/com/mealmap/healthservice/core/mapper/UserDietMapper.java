package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.diet.UserDietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;
import com.mealmap.healthservice.entity.UserDiet;
import com.mealmap.healthservice.entity.UserPhysicHealth;
import com.mealmap.healthservice.entity.UserPhysicHealthHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDietMapper {
    UserDietDto entityToDto(UserDiet entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    UserDiet dtoToEntity(UserDietCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    void updateEntityFromDto(@MappingTarget UserDiet entity, UserDietUpdatingDto dto);
}
