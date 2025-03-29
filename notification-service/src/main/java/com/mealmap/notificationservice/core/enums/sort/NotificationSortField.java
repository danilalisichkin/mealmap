package com.mealmap.notificationservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationSortField {
    ID("id"),
    SENT_AT("sentAt");

    private final String value;
}