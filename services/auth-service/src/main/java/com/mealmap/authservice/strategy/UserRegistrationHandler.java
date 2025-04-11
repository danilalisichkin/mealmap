package com.mealmap.authservice.strategy;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.Role;

public interface UserRegistrationHandler {
    UserDto handle(UserRegisterDto registerDto);

    Role getSupportedUserRole();
}
