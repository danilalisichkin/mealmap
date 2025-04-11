package com.mealmap.userservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    OPERATOR(1),
    CUSTOMER(2),
    SUPPLIER(3),
    ADMIN(4);

    private final Integer id;

    public static Role fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(Role.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
