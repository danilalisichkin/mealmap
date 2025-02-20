package com.mealmap.userservice.entity.enums;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@Convert
public enum StatusEvent {
    ACTIVATE(1),
    DEACTIVATE(2),
    BLOCK(3),
    TEMPORARY_BLOCK(4),
    UNBLOCK(5);

    private final Integer id;

    public static StatusEvent fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(StatusEvent.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
