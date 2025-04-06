package com.mealmap.userservice.strategy;

import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.userservice.service.UserStatusHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UserStatusChangingBaseHandler implements UserStatusChangingHandler {
    protected final UserStatusHistoryService statusHistoryService;

    protected final NotificationClient notificationClient;
}
