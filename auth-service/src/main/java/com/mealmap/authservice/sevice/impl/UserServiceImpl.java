package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.authservice.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final KeycloakResourceService kcResourceService;

    private final KeycloakRoleService kcRoleService;

    @Override
    public List<RoleRepresentation> getUserRoles(UUID id) {
        UserResource userResource = kcResourceService.findUserResourceByUserId(id.toString());

        return kcRoleService.getAllUserRoles(userResource);
    }

    @Override
    public UserRepresentation getUserById(UUID id) {
        return kcResourceService.findUserRepresentationByUserId(id.toString());
    }

    @Override
    public void deleteUser(UUID id) {
        kcResourceService.deleteUser(id.toString());
    }

    @Override
    public void assignRoleToUser(UUID id, UserRole role) {
        UserResource userResource = kcResourceService.findUserResourceByUserId(id.toString());

        kcRoleService.assignRoleToUser(role, userResource);
    }

    @Override
    public void unassignRoleFromUser(UUID id, UserRole role) {
        UserResource userResource = kcResourceService.findUserResourceByUserId(id.toString());

        kcRoleService.unassignRoleFromUser(role, userResource);
    }
}
