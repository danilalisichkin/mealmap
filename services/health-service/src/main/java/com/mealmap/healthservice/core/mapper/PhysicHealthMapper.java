package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.health.PhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthUpdatingDto;
import com.mealmap.healthservice.entity.PhysicHealth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhysicHealthMapper {
    PhysicHealthDto entityToDto(PhysicHealth entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "history", ignore = true)
    PhysicHealth dtoToEntity(PhysicHealthCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "diet", ignore = true)
    @Mapping(target = "history", ignore = true)
    void updateEntityFromDto(@MappingTarget PhysicHealth entity, PhysicHealthUpdatingDto dto);
}
