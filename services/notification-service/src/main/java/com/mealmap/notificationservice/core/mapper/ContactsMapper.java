package com.mealmap.notificationservice.core.mapper;

import com.mealmap.notificationservice.core.dto.contacts.UserContactsDto;
import com.mealmap.notificationservice.doc.UserContacts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactsMapper {
    UserContactsDto docToDto(UserContacts doc);
}
