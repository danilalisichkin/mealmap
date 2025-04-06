package com.mealmap.notificationservice.strategy.impl;

import com.mealmap.notificationservice.config.NotificationsConfig;
import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.mapper.NotificationMapper;
import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.repository.NotificationRepository;
import com.mealmap.notificationservice.strategy.NotificationBaseHandler;
import com.mealmap.starters.exceptionstarter.exception.ForbiddenException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.notificationservice.core.constant.MailTemplates.SENDER_TEMPLATE;
import static com.mealmap.notificationservice.core.message.ApplicationMessages.NOTIFICATION_METHOD_IS_DISABLED;
import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_EMAIL_NOT_SET;

@Component
public class EmailNotificationHandler extends NotificationBaseHandler {
    private final NotificationsConfig notificationsConfig;

    private final JavaMailSender mailSender;

    @Autowired
    protected EmailNotificationHandler(
            NotificationMapper notificationMapper,
            NotificationRepository notificationRepository,
            NotificationsConfig notificationsConfig,
            JavaMailSender mailSender) {

        super(notificationMapper, notificationRepository);
        this.notificationsConfig = notificationsConfig;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional
    public NotificationDto handle(UserContacts userContacts, NotificationCreationDto notificationDto) {
        if (!notificationsConfig.getEmail().isEnabled()) {
            throw new ForbiddenException(NOTIFICATION_METHOD_IS_DISABLED);
        }

        String email = userContacts.getEmail();

        if (email == null || StringUtils.isEmpty(email)) {
            throw new ResourceNotFoundException(USER_EMAIL_NOT_SET.formatted(userContacts.getUserId()));
        }

        sendSimpleMessage(email, notificationDto.getSubject(), notificationDto.getMessage());

        Notification notificationToCreate = initDefaultNotification(userContacts, notificationDto);

        return notificationMapper.docToDto(
                notificationRepository.save(notificationToCreate));
    }

    @Override
    public Channel getSupportedChannel() {
        return Channel.EMAIL;
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        String sender = SENDER_TEMPLATE.formatted(
                notificationsConfig.getEmail().getSender().getName(),
                notificationsConfig.getEmail().getSender().getEmail());
        message.setFrom(sender);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
