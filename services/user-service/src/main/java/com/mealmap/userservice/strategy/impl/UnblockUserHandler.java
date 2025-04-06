package com.mealmap.userservice.strategy.impl;

import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.enums.Channel;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.service.UserStatusHistoryService;
import com.mealmap.userservice.strategy.UserStatusChangingBaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_IS_NOT_BLOCKED;
import static com.mealmap.userservice.notification.NotificationTemplates.YOU_HAVE_BEEN_UNBLOCKED_MESSAGE;
import static com.mealmap.userservice.notification.NotificationTemplates.YOU_HAVE_BEEN_UNBLOCKED_SUBJECT;

@Component
public class UnblockUserHandler extends UserStatusChangingBaseHandler {
    @Autowired
    public UnblockUserHandler(
            UserStatusHistoryService statusHistoryService,
            NotificationClient notificationClient) {

        super(statusHistoryService, notificationClient);
    }

    @Override
    public StatusHistoryDto handle(User user, StatusHistoryCreationDto statusDto) {
        boolean isNotBlocked = !user.getStatus().getIsTemporaryBlocked() && !user.getStatus().getIsBlocked();

        if (isNotBlocked) {
            throw new BadRequestException(USER_IS_NOT_BLOCKED);
        } else {
            user.getStatus().setIsTemporaryBlocked(false);
            user.getStatus().setIsBlocked(false);
        }

        //TODO: maybe send notification using AOP ???
        notificationClient.sendNotification(new Notification(
                user.getId(),
                Channel.EMAIL,
                YOU_HAVE_BEEN_UNBLOCKED_SUBJECT,
                YOU_HAVE_BEEN_UNBLOCKED_MESSAGE.formatted(
                        user.getFirstName())
        ));

        return statusHistoryService.createUserStatusHistoryElement(getSupportedEvent(), user, statusDto);
    }

    @Override
    public StatusEvent getSupportedEvent() {
        return StatusEvent.UNBLOCK;
    }
}
