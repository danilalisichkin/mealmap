package com.mealmap.authservice.strategy.impl;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.authservice.strategy.UserRegistrationBaseHandler;
import com.mealmap.authservice.util.UserDefaults;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientEmployeeRegistrationHandler extends UserRegistrationBaseHandler {
    @Autowired
    public ClientEmployeeRegistrationHandler(
            KeycloakResourceService kcResourceService, KeycloakRoleService kcRoleService, UserMapper userMapper) {

        super(kcResourceService, kcRoleService, userMapper);
    }

    @Override
    public UserDto handle(UserRegisterDto registerDto) {
        // TODO: check if organization exists, connect with notification service
        UserRepresentation registeredUser =
                createUser(registerDto, getSupportedUserRole(), UserDefaults.status());

        UserDto userDto = userMapper.keycloakRepresentationToDto(registeredUser);
        userDto.setRole(getSupportedUserRole());

        return userDto;
    }

    @Override
    public UserRole getSupportedUserRole() {
        return UserRole.CLIENT_EMPLOYEE;
    }
}
