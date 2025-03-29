package com.mealmap.notificationservice.strategy.manager;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.strategy.NotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationManager {
    private final Map<Channel, NotificationHandler> handlers;

    @Autowired
    public NotificationManager(List<NotificationHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        NotificationHandler::getSupportedChannel,
                        handler -> handler));
    }

    public NotificationDto processNotificationCreation(UserContacts userContacts, NotificationCreationDto notificationDto) {
        return handlers.get(notificationDto.getChannel()).handle(userContacts, notificationDto);
    }
}
