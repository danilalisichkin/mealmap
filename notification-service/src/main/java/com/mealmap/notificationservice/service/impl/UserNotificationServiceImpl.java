package com.mealmap.notificationservice.service.impl;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.dto.page.PageDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.notificationservice.core.mapper.NotificationMapper;
import com.mealmap.notificationservice.core.mapper.PageMapper;
import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.doc.UserContacts;
import com.mealmap.notificationservice.exception.ResourceNotFoundException;
import com.mealmap.notificationservice.repository.NotificationRepository;
import com.mealmap.notificationservice.repository.UserContactsRepository;
import com.mealmap.notificationservice.service.UserNotificationService;
import com.mealmap.notificationservice.strategy.manager.NotificationManager;
import com.mealmap.notificationservice.util.PageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.notificationservice.core.message.ApplicationMessages.USER_CONTACTS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserNotificationServiceImpl implements UserNotificationService {
    private final NotificationManager notificationManager;

    private final PageMapper pageMapper;

    private final NotificationMapper notificationMapper;

    private final UserContactsRepository userContactsRepository;

    private final NotificationRepository notificationRepository;

    @Override
    public PageDto<NotificationDto> getPageOfNotifications(
            String userId, Integer offset, Integer limit, NotificationSortField sortBy, Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Page<Notification> notifications = notificationRepository.findAllByUserId(userId, request);

        return pageMapper.pageToPageDto(
                notificationMapper.docPageToDtoPage(notifications));
    }

    @Override
    @Transactional
    public NotificationDto createNotification(String userId, NotificationCreationDto notificationDto) {
        UserContacts userContacts = getUserContactsDoc(userId);

        return notificationManager.processNotificationCreation(userContacts, notificationDto);
    }

    private UserContacts getUserContactsDoc(String userId) {
        return userContactsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_CONTACTS_NOT_FOUND.formatted(userId)));
    }
}
