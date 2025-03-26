package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.health.PhysicHealthHistoryDto;
import com.mealmap.healthservice.entity.PhysicHealthHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhysicHealthHistoryMapper {
    PhysicHealthHistoryDto entityToDto(PhysicHealthHistory entity);

    List<PhysicHealthHistoryDto> entityListToDtoList(List<PhysicHealthHistory> entities);
}
