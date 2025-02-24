package com.mealmap.authservice.strategy;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.enums.UserRole;

public interface UserRegistrationHandler {
    UserDto handle(UserRegisterDto registerDto);

    UserRole getSupportedUserRole();
}
