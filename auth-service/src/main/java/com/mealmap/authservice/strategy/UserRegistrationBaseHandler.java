package com.mealmap.authservice.strategy;

import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.dto.UserStatus;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.authservice.util.UserAttributesBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UserRegistrationBaseHandler implements UserRegistrationHandler {
    protected final KeycloakResourceService kcResourceService;

    protected final KeycloakRoleService kcRoleService;

    protected final UserMapper userMapper;

    protected UserRepresentation createUser(UserRegisterDto userRegisterDto, UserRole role, UserStatus status) {
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
        kcRoleService.assignRoleToUser(role, userResource);

        return user;
    }
}
