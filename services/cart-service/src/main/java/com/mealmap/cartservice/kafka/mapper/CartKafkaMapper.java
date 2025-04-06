package com.mealmap.cartservice.kafka.mapper;

import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartKafkaMapper {
    Cart dtoToEntity(KafkaCartCreationDto dto);
}
