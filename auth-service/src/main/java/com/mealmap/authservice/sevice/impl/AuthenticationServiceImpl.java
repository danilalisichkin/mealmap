package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.core.dto.KeycloakAccessTokenDto;
import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserLoginDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.mapper.KeycloakAccessTokenMapper;
import com.mealmap.authservice.sevice.AuthenticationService;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.strategy.manager.UserRegistrationManager;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final KeycloakResourceService kcResourceService;

    private final KeycloakAccessTokenMapper accessTokenMapper;

    private final UserRegistrationManager registrationManager;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        return registrationManager.processUserRegistration(userRegisterDto);
    }

    @Override
    public KeycloakAccessTokenDto loginUser(UserLoginDto userLoginDto) {
        AccessTokenResponse token = kcResourceService.getUserAccessToken(
                userLoginDto.getIdentifier(),
                userLoginDto.getPassword());

        return accessTokenMapper.tokenToDto(token);
    }

    @Override
    public KeycloakAccessTokenDto refreshUserAccessToken(String refreshToken) {
        AccessTokenResponse token = kcResourceService.refreshUserAccessToken(refreshToken);

        return accessTokenMapper.tokenToDto(token);
    }
}
