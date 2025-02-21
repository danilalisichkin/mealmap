package com.mealmap.authservice.controller.api;

import com.mealmap.authservice.core.dto.KeycloakAccessTokenDto;
import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserLoginDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/sign-in")
    public ResponseEntity<KeycloakAccessTokenDto> signIn(@RequestBody UserLoginDto loginDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserRegisterDto registerDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<KeycloakAccessTokenDto> refreshToken(@RequestBody String refreshToken) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
