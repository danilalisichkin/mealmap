package com.mealmap.authservice.sevice;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface KeycloakRoleService {
    void assignRoleToUser(UserResource userResource, String role);

    void unassignRoleFromUser(UserResource userResource, String role);

    List<RoleRepresentation> getAllUserRoles(UserResource userResource);
}
