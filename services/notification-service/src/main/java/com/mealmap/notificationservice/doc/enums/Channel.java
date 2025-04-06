package com.mealmap.notificationservice.doc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Channel {
    TELEGRAM(1),
    EMAIL(2),
    SMS(3);

    private final Integer id;

    public static Channel fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(Channel.values())
                        .filter(channel -> channel.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
