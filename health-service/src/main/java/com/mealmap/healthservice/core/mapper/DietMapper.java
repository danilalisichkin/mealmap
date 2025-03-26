package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.diet.DietCreationDto;
import com.mealmap.healthservice.core.dto.diet.DietDto;
import com.mealmap.healthservice.core.dto.diet.DietUpdatingDto;
import com.mealmap.healthservice.entity.Diet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DietMapper {
    DietDto entityToDto(Diet entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    Diet dtoToEntity(DietCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "physicHealth", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    void updateEntityFromDto(@MappingTarget Diet entity, DietUpdatingDto dto);
}
