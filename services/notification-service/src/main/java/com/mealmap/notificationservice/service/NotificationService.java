package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

public interface NotificationService {
    PageDto<NotificationDto> getPageOfNotifications(
            Integer offset, Integer limit, NotificationSortField sortBy, Sort.Direction sortOrder,
            NotificationFilter filter, String search);
}
