package com.mealmap.authservice.strategy.impl;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.UserService;
import com.mealmap.authservice.strategy.UserRegistrationBaseHandler;
import com.mealmap.authservice.util.UserDefaults;
import com.mealmap.authservice.validator.UserOrganizationValidator;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientEmployeeRegistrationHandler extends UserRegistrationBaseHandler {
    private final UserOrganizationValidator userOrganizationValidator;

    @Autowired
    public ClientEmployeeRegistrationHandler(
            UserService userService,
            UserMapper userMapper,
            UserOrganizationValidator userOrganizationValidator) {

        super(userService, userMapper);
        this.userOrganizationValidator = userOrganizationValidator;
    }

    @Override
    public UserDto handle(UserRegisterDto registerDto) {
        // TODO: connect with notification service
        userOrganizationValidator.validateUserRelationToOrganization(
                registerDto.getOrganizationId(), getSupportedUserRole());

        UserRepresentation registeredUser =
                userService.createUser(registerDto, getSupportedUserRole(), UserDefaults.status());

        UserDto userDto = userMapper.keycloakRepresentationToDto(registeredUser);
        userDto.setRole(getSupportedUserRole());

        return userDto;
    }

    @Override
    public UserRole getSupportedUserRole() {
        return UserRole.CLIENT_EMPLOYEE;
    }
}
