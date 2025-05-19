package com.mealmap.authservice.controller.api;

import com.mealmap.authservice.controller.doc.AuthControllerDoc;
import com.mealmap.authservice.core.dto.KeycloakAccessTokenDto;
import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserLoginDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.sevice.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthControllerDoc {
    private final AuthenticationService authService;

    @Override
    @PostMapping("/sign-in")
    public ResponseEntity<KeycloakAccessTokenDto> signIn(@RequestBody @Valid UserLoginDto loginDto) {
        KeycloakAccessTokenDto accessTokens = authService.loginUser(loginDto);

        return ResponseEntity.status(HttpStatus.OK).body(accessTokens);
    }

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserRegisterDto registerDto) {
        UserDto registeredUserDto = authService.registerUser(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUserDto);
    }

    @Override
    @PostMapping("/refresh-token")
    public ResponseEntity<KeycloakAccessTokenDto> refreshToken(@RequestBody @NotEmpty String refreshToken) {
        KeycloakAccessTokenDto accessTokens = authService.refreshUserAccessToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).body(accessTokens);
    }
}
