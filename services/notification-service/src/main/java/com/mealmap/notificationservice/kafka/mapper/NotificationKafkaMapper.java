package com.mealmap.notificationservice.kafka.mapper;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.kafka.dto.KafkaNotificationCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationKafkaMapper {
    NotificationCreationDto kafkaDtoToCreationDto(KafkaNotificationCreationDto kafkaDto);
}
