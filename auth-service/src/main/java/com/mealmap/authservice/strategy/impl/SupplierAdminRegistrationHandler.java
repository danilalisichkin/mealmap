package com.mealmap.authservice.strategy.impl;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.UserService;
import com.mealmap.authservice.strategy.UserRegistrationBaseHandler;
import com.mealmap.authservice.util.UserDefaults;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierAdminRegistrationHandler extends UserRegistrationBaseHandler {
    @Autowired
    public SupplierAdminRegistrationHandler(UserService userService, UserMapper userMapper) {
        super(userService, userMapper);
    }

    @Override
    public UserDto handle(UserRegisterDto registerDto) {
        // TODO: check if organization exists, connect with notification service
        UserRepresentation registeredUser =
                userService.createUser(registerDto, getSupportedUserRole(), UserDefaults.supplierAdminStatus());

        UserDto userDto = userMapper.keycloakRepresentationToDto(registeredUser);
        userDto.setRole(getSupportedUserRole());

        return userDto;
    }

    @Override
    public UserRole getSupportedUserRole() {
        return UserRole.SUPPLIER_ADMIN;
    }
}
