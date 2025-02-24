package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.exception.BadRequestException;
import com.mealmap.authservice.exception.ResourceNotFoundException;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.mealmap.authservice.core.message.ApplicationMessages.USER_NOT_FOUND;
import static com.mealmap.authservice.core.message.ApplicationMessages.USER_ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class KeycloakRoleServiceImpl implements KeycloakRoleService {
    private final RolesResource rolesResource;

    @Override
    public void assignRoleToUser(UserRole role, UserResource userResource) {
        RoleRepresentation keycloakRole = getRoleRepresentation(role);

        try {
            userResource.roles().realmLevel().add(Collections.singletonList(keycloakRole));
        } catch (NotFoundException e) {
            throw new BadRequestException(USER_NOT_FOUND);
        }
    }

    @Override
    public void unassignRoleFromUser(UserRole role, UserResource userResource) {
        RoleRepresentation keycloakRole = getRoleRepresentation(role);

        try {
            userResource.roles().realmLevel().remove(Collections.singletonList(keycloakRole));
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(USER_NOT_FOUND);
        }
    }

    @Override
    public List<RoleRepresentation> getAllUserRoles(UserResource userResource) {
        try {
            return userResource.roles().realmLevel().listAll();
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(USER_NOT_FOUND);
        }
    }

    private RoleRepresentation getRoleRepresentation(UserRole role) {
        try {
            return rolesResource.get(role.name()).toRepresentation();
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(USER_ROLE_NOT_FOUND);
        }
    }
}
