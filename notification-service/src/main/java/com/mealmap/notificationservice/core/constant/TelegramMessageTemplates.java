package com.mealmap.notificationservice.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TelegramMessageTemplates {
    public static final String MESSAGE_WITH_SUBJECT_TEMPLATE = "%s\n\n%s";
}
