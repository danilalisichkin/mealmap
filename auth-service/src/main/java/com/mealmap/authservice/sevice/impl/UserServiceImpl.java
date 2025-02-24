package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.dto.UserStatus;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.authservice.sevice.UserService;
import com.mealmap.authservice.util.UserAttributesBuilder;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final KeycloakResourceService kcResourceService;

    private final KeycloakRoleService kcRoleService;

    @Override
    public UserRepresentation createUser(UserRegisterDto userRegisterDto, UserRole role, UserStatus status) {
        Map<String, List<String>> organizationAsAttributes =
                UserAttributesBuilder.buildFromOrganizationId(userRegisterDto.getOrganizationId());
        Map<String, List<String>> statusAsAttributes =
                UserAttributesBuilder.buildFromStatus(status);

        Map<String, List<String>> attributes = new HashMap<>(organizationAsAttributes);
        attributes.putAll(statusAsAttributes);

        UserRepresentation user = kcResourceService.createUser(
                userRegisterDto.getPhoneNumber(),
                userRegisterDto.getEmail(),
                userRegisterDto.getFirstName(),
                userRegisterDto.getLastName(),
                userRegisterDto.getPassword(),
                attributes);

        UserResource userResource = kcResourceService.findUserResourceByUserId(user.getId());
        kcRoleService.assignRoleToUser(userResource, role.name());

        return user;
    }
}
