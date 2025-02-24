package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.dto.KeycloakAccessTokenDto;
import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserLoginDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;

public interface AuthenticationService {
    UserDto registerUser(UserRegisterDto userRegisterDto);

    KeycloakAccessTokenDto loginUser(UserLoginDto userLoginDto);

    KeycloakAccessTokenDto refreshUserAccessToken(String refreshToken);
}
