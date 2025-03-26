package com.mealmap.recommendationservice.core.mapper;

import com.mealmap.recommendationservice.client.dto.health.UserPhysicHealthDto;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HealthMapper {
    PhysicHealth dtoToModel(UserPhysicHealthDto dto);
}
