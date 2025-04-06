package com.mealmap.authservice.keycloak.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserAttributes {
    public static final String ORGANIZATION_ID = "organizationId";
    public static final String IS_ACTIVE = "isActive";
    public static final String IS_TEMPORARY_BLOCKED = "isTemporaryBlocked";
    public static final String IS_BLOCKED = "isBlocked";
}
