package com.mealmap.authservice.client.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationType {
    OPERATOR,
    SUPPLIER,
    CUSTOMER
}
