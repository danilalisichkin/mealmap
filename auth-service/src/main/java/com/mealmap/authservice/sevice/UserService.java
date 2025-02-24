package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.enums.UserRole;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<RoleRepresentation> getUserRoles(UUID id);

    UserRepresentation getUserById(UUID id);

    void deleteUser(UUID id);

    void assignRoleToUser(UUID id, UserRole role);

    void unassignRoleFromUser(UUID id, UserRole role);
}
