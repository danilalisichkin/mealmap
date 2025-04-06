package com.mealmap.starters.notificationstarter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Channel {
    TELEGRAM,
    EMAIL,
    SMS;
}
