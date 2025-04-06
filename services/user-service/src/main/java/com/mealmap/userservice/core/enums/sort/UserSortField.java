package com.mealmap.userservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSortField {
    ID("id"),
    PHONE_NUMBER("phoneNumber"),
    EMAIL("email"),
    FIRST_NAME("firstName"),
    ORGANIZATION("organizationId"),
    CREATED_AT("createdAt");

    private final String value;
}
