package com.mealmap.telegrambot.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CommandType {
    ECHO("/echo"),
    START("/start");

    private final String prefix;

    public static CommandType fromPrefix(final String prefix) {
        return prefix == null ? null :
                Arrays.stream(CommandType.values())
                        .filter(type -> type.getPrefix().equals(prefix))
                        .findFirst()
                        .orElse(null);
    }
}
