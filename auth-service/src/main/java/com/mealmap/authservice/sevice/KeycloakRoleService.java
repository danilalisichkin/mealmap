package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.enums.UserRole;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface KeycloakRoleService {
    void assignRoleToUser(UserRole role, UserResource userResource);

    void unassignRoleFromUser(UserRole role, UserResource userResource);

    List<RoleRepresentation> getAllUserRoles(UserResource userResource);
}
