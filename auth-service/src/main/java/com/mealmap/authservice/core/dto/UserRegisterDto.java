package com.mealmap.authservice.core.dto;

import com.mealmap.authservice.core.enums.UserRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegisterDto {
    String phoneNumber;

    String email;

    String password;

    String firstName;

    String lastName;

    UserRole role;
}
