package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.health.UserPhysicHealthHistoryDto;
import com.mealmap.healthservice.entity.UserPhysicHealthHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPhysicHealthHistoryMapper {
    UserPhysicHealthHistoryDto entityToDto(UserPhysicHealthHistory entity);

    List<UserPhysicHealthHistoryDto> entityListToDtoList(List<UserPhysicHealthHistory> entities);
}
