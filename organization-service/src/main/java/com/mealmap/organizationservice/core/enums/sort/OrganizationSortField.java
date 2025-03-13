package com.mealmap.organizationservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationSortField {
    ID("id"),
    UNP("unp"),
    NAME("name"),
    LEGAL_ADDRESS("legalAddress"),
    PHONE_NUMBER("phoneNumber"),
    EMAIL("email"),
    TYPE("type"),
    CREATED_AT("createdAt");

    private final String value;
}
