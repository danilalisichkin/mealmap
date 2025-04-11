package com.mealmap.authservice.kafka.mapper;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.kafka.dto.KafkaUserCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserKafkaMapper {
    KafkaUserCreationDto dtoToCreationDto(UserDto dto);
}
