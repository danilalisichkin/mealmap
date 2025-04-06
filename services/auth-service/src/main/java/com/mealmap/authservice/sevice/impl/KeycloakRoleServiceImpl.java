package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
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
    public void assignRoleToUser(UserResource userResource, String role) {
        RoleRepresentation keycloakRole = getRoleRepresentation(role);

        try {
            userResource.roles().realmLevel().add(Collections.singletonList(keycloakRole));
        } catch (NotFoundException e) {
            throw new BadRequestException(USER_NOT_FOUND);
        }
    }

    @Override
    public void unassignRoleFromUser(UserResource userResource, String role) {
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

    private RoleRepresentation getRoleRepresentation(String role) {
        try {
            return rolesResource.get(role).toRepresentation();
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(USER_ROLE_NOT_FOUND);
        }
    }
}
