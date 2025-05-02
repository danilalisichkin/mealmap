package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.core.dto.contacts.UserContactsDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

public interface UserNotificationService {
    PageDto<NotificationDto> getPageOfNotifications(
            String userId, Integer offset, Integer limit, NotificationSortField sortBy, Sort.Direction sortOrder);

    UserContactsDto getContacts(String userId);

    NotificationDto createNotification(String userId, NotificationCreationDto notificationDto);
}
