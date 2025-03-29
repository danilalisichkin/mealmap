package com.mealmap.notificationservice.core.mapper;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.doc.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    NotificationDto docToDto(Notification doc);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    Notification dtoToDoc(NotificationCreationDto dto);

    List<NotificationDto> docListToDtoList(List<Notification> docList);

    default Page<NotificationDto> docPageToDtoPage(Page<Notification> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
