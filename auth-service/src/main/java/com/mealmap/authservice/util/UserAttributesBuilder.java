package com.mealmap.authservice.util;

import com.mealmap.authservice.core.dto.UserStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_ACTIVE;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_BLOCKED;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_TEMPORARY_BLOCKED;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.ORGANIZATION_ID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserAttributesBuilder {
    public static Map<String, List<String>> buildFromOrganizationId(Integer organizationId) {
        return organizationId == null
                ? Collections.emptyMap()
                : Map.of(ORGANIZATION_ID, List.of(String.valueOf(organizationId)));
    }

    public static Map<String, List<String>> buildFromStatus(UserStatus status) {
        return status == null
                ? Collections.emptyMap()
                : Map.of(
                IS_ACTIVE, List.of(status.getIsActive().toString()),
                IS_TEMPORARY_BLOCKED, List.of(status.getIsTemporaryBlocked().toString()),
                IS_BLOCKED, List.of(status.getIsBlocked().toString()));
    }
}
