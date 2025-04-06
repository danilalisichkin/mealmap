package com.mealmap.notificationservice.strategy.impl;

import com.mealmap.notificationservice.config.NotificationsConfig;
import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.mapper.NotificationMapper;
import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.exception.ForbiddenException;
import com.mealmap.notificationservice.exception.ResourceNotFoundException;
import com.mealmap.notificationservice.repository.NotificationRepository;
import com.mealmap.notificationservice.strategy.NotificationBaseHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.notificationservice.core.message.ApplicationMessages.NOTIFICATION_METHOD_IS_DISABLED;
import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_PHONE_NOT_SET;

@Component
public class SmsNotificationHandler extends NotificationBaseHandler {
    private final NotificationsConfig notificationsConfig;

    @Autowired
    protected SmsNotificationHandler(
            NotificationMapper notificationMapper,
            NotificationRepository notificationRepository,
            NotificationsConfig notificationsConfig) {

        super(notificationMapper, notificationRepository);
        this.notificationsConfig = notificationsConfig;
    }

    @Override
    @Transactional
    public NotificationDto handle(UserContacts userContacts, NotificationCreationDto notificationDto) {
        if (!notificationsConfig.getSms().isEnabled()) {
            throw new ForbiddenException(NOTIFICATION_METHOD_IS_DISABLED);
        }

        String phoneNumber = userContacts.getPhoneNumber();

        if (phoneNumber == null || StringUtils.isEmpty(phoneNumber)) {
            throw new ResourceNotFoundException(USER_PHONE_NOT_SET.formatted(userContacts.getUserId()));
        }

        Notification notificationToCreate = initDefaultNotification(userContacts, notificationDto);

        //TODO: send SMS (mb in future)

        return notificationMapper.docToDto(
                notificationRepository.save(notificationToCreate));
    }

    @Override
    public Channel getSupportedChannel() {
        return Channel.SMS;
    }
}
