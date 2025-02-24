package com.mealmap.userservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserRole {
    CLIENT_HEAD(1),
    CLIENT_EMPLOYEE(2),
    SUPPLIER_EMPLOYEE(3),
    SUPPLIER_ADMIN(4),
    ADMIN(5);

    private final Integer id;

    public static UserRole fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(UserRole.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
