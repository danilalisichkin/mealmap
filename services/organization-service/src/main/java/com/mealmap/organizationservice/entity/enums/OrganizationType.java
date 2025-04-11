package com.mealmap.organizationservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrganizationType {
    OPERATOR(1),
    SUPPLIER(2),
    CUSTOMER(3);

    private final Integer id;

    public static OrganizationType fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(OrganizationType.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
