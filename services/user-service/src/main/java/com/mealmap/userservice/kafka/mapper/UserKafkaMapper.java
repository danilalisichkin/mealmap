package com.mealmap.userservice.kafka.mapper;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserKafkaMapper {
    User creationDtoToEntity(KafkaUserCreationDto creationDto);

    KafkaUserUpdateDto entityToUpdateDto(User user);

    KafkaUserStatusUpdateDto entityToStatusUpdateDto(User user);
}
