package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.health.UserPhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;
import com.mealmap.healthservice.entity.UserPhysicHealth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPhysicHealthMapper {
    UserPhysicHealthDto entityToDto(UserPhysicHealth entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "history", ignore = true)
    UserPhysicHealth dtoToEntity(UserPhysicHealthCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "history", ignore = true)
    void updateEntityFromDto(@MappingTarget UserPhysicHealth entity, UserPhysicHealthUpdatingDto dto);
}
