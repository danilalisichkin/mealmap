package com.mealmap.authservice.strategy.impl;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.Role;
import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.UserService;
import com.mealmap.authservice.strategy.UserRegistrationBaseHandler;
import com.mealmap.authservice.util.UserDefaults;
import com.mealmap.authservice.validator.UserOrganizationValidator;
import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.enums.Channel;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mealmap.authservice.notification.NotificationTemplates.USER_ACTIVATION_IS_PENDING_SUBJECT;
import static com.mealmap.authservice.notification.NotificationTemplates.USER_ACTIVATION_IS_PENDING_MESSAGE;

@Component
public class CustomerRegistrationHandler extends UserRegistrationBaseHandler {
    private final NotificationClient notificationClient;

    @Autowired
    public CustomerRegistrationHandler(
            UserService userService,
            UserMapper userMapper,
            UserOrganizationValidator userOrganizationValidator,
            NotificationClient notificationClient) {

        super(userOrganizationValidator, userService, userMapper);
        this.notificationClient = notificationClient;
    }

    @Override
    public UserDto handle(UserRegisterDto registerDto) {
        userOrganizationValidator.validateUserRelationToOrganization(
                registerDto.getOrganizationId(), getSupportedUserRole());

        UserRepresentation registeredUser =
                userService.createUser(registerDto, getSupportedUserRole(), UserDefaults.status());

        UserDto userDto = userMapper.keycloakRepresentationToDto(registeredUser);
        userDto.setRole(getSupportedUserRole());

        notificationClient.sendNotification(new Notification(
                userDto.getId(),
                Channel.EMAIL,
                USER_ACTIVATION_IS_PENDING_SUBJECT,
                USER_ACTIVATION_IS_PENDING_MESSAGE));

        return userDto;
    }

    @Override
    public Role getSupportedUserRole() {
        return Role.CUSTOMER;
    }
}
