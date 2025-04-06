package com.mealmap.userservice.kafka.mapper;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.kafka.dto.KafkaUserPreferencesCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPreferencesKafkaMapper {
    KafkaUserPreferencesCreationDto userToPreferencesCreationDto(User user);
}
