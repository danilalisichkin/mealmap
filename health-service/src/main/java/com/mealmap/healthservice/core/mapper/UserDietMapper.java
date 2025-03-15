package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.diet.UserDietUpdatingDto;
import com.mealmap.healthservice.entity.UserDiet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDietMapper {
    UserDietDto entityToDto(UserDiet entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    UserDiet dtoToEntity(UserDietCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    void updateEntityFromDto(@MappingTarget UserDiet entity, UserDietUpdatingDto dto);
}
