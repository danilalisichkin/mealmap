package com.mealmap.notificationservice.strategy;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.doc.enums.Channel;

public interface NotificationHandler {
    NotificationDto handle(UserContacts userContacts, NotificationCreationDto notificationDto);

    Channel getSupportedChannel();
}
