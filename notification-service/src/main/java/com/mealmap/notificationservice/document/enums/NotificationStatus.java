package com.mealmap.notificationservice.document.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum NotificationStatus {
    SENT(1),
    FAILED(2),
    PENDING(3);

    private final Integer id;

    public static NotificationStatus fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(NotificationStatus.values())
                        .filter(channel -> channel.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
