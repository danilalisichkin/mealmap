package com.mealmap.authservice.strategy.manager;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.UserRole;
import com.mealmap.authservice.strategy.UserRegistrationHandler;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mealmap.authservice.core.message.ApplicationMessages.REGISTER_USER_WITH_ROLE_PROHIBITED;

@Component
public class UserRegistrationManager {
    private final Map<UserRole, UserRegistrationHandler> handlers;

    @Autowired
    public UserRegistrationManager(List<UserRegistrationHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        UserRegistrationHandler::getSupportedUserRole,
                        handler -> handler));
    }

    public UserDto processUserRegistration(UserRegisterDto registerDto) {
        UserRegistrationHandler handler = handlers.get(registerDto.getRole());

        if (handler == null) {
            throw new BadRequestException(REGISTER_USER_WITH_ROLE_PROHIBITED);
        } else {
            return handler.handle(registerDto);
        }
    }
}
