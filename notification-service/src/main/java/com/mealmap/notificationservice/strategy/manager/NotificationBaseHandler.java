package com.mealmap.notificationservice.strategy.manager;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.mapper.NotificationMapper;
import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;
import com.mealmap.notificationservice.repository.NotificationRepository;
import com.mealmap.notificationservice.strategy.NotificationHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NotificationBaseHandler implements NotificationHandler {
    protected final NotificationMapper notificationMapper;

    protected final NotificationRepository notificationRepository;

    protected Notification initDefaultNotification(UserContacts userContacts, NotificationCreationDto notificationDto) {
        Notification newNotification = notificationMapper.dtoToDoc(notificationDto);
        newNotification.setUserId(userContacts.getUserId());
        newNotification.setStatus(NotificationStatus.SENT);
        newNotification.setSentAt(LocalDateTime.now());

        return newNotification;
    }
}
