package com.mealmap.notificationservice.service;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.querydsl.core.types.Predicate;

public interface NotificationPredicateService {
    Predicate buildPredicateForNotifications(NotificationFilter filter, String search);
}
