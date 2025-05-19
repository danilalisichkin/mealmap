package com.mealmap.authservice.controller.doc;

import com.mealmap.authservice.core.dto.KeycloakAccessTokenDto;
import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.core.dto.UserLoginDto;
import com.mealmap.authservice.core.dto.UserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth API Controller", description = "Предоставляет функционал для аутентификации пользователя")
public interface AuthControllerDoc {

    @Operation(
            summary = "Sign in",
            description = "Позволяет пользователю войти в приложение")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: неверный идентификатор или пароль")
    })
    ResponseEntity<KeycloakAccessTokenDto> signIn(
            @Parameter(
                    name = "Учетные данные пользователя",
                    description = "Учетные данные, необходимые для входа в систему",
                    required = true)
            @RequestBody @Valid UserLoginDto loginDto);

    @Operation(
            summary = "Sign up",
            description = "Позволяет новому пользователю зарегистрироваться в приложении")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: номер телефона или электронная почта уже используется")
    })
    ResponseEntity<UserDto> signUp(
            @Parameter(
                    name = "Учетные данные пользователя",
                    description = "Подробная информация, требуемая для регистрации пользователя",
                    required = true)
            @RequestBody @Valid UserRegisterDto registerDto);

    @Operation(
            summary = "Refresh token",
            description = "Позволяет пользователю освежить свой токен доступа, используя токен обновления")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request: invalid parameters or refresh token expired")
    })
    ResponseEntity<KeycloakAccessTokenDto> refreshToken(
            @Parameter(
                    name = "Токен обновления",
                    description = "Токен обновления, используемый для получения нового токена доступа",
                    required = true)
            @RequestBody @NotEmpty String refreshToken);
}
