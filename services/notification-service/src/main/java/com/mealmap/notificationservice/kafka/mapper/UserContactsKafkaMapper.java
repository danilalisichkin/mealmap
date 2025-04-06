package com.mealmap.notificationservice.kafka.mapper;

import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.kafka.dto.KafkaUserContactsUpdateTgChatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserContactsKafkaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "tgChatId", source = "chatId")
    void updateDocFromDto(@MappingTarget UserContacts doc, final KafkaUserContactsUpdateTgChatDto dto);
}
