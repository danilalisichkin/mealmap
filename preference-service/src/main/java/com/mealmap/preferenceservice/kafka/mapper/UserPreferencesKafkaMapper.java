package com.mealmap.preferenceservice.kafka.mapper;

import com.mealmap.preferenceservice.entity.UserPreferences;
import com.mealmap.preferenceservice.kafka.dto.KafkaUserPreferencesCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPreferencesKafkaMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productPreferences", ignore = true)
    @Mapping(target = "categoryPreferences", ignore = true)
    UserPreferences dtoToEntity(KafkaUserPreferencesCreationDto dto);
}
