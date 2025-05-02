package com.mealmap.userservice.kafka.mapper;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserContactsUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserContactsKafkaMapper {
    @Mapping(target = "userId", source = "id")
    KafkaUserContactsCreationDto userToContactsCreationDto(User user);

    @Mapping(target = "userId", source = "id")
    KafkaUserContactsUpdateDto userToContactsUpdateDto(User user);

}
