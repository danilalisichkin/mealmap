package com.mealmap.userservice.kafka.mapper;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.kafka.dto.KafkaCartCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartKafkaMapper {
    @Mapping(target = "userId", source = "id")
    KafkaCartCreationDto userToCartCreationDto(User user);
}
