package com.mealmap.authservice.keycloak.util;

import com.mealmap.authservice.core.message.ApplicationMessages;
import com.mealmap.authservice.exception.BadRequestException;
import com.mealmap.authservice.exception.ConflictException;
import com.mealmap.authservice.exception.UnauthorizedException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.ErrorRepresentation;
import org.springframework.http.HttpStatus;

import static com.mealmap.authservice.keycloak.util.KeycloakErrorMessages.INVALID_REFRESH_TOKEN;
import static com.mealmap.authservice.keycloak.util.KeycloakErrorMessages.REFRESH_TOKEN_EXPIRED;
import static com.mealmap.authservice.keycloak.util.KeycloakErrorMessages.USER_WITH_SAME_EMAIL_EXISTS;
import static com.mealmap.authservice.keycloak.util.KeycloakErrorMessages.USER_WITH_SAME_USERNAME_EXISTS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeycloakResponseValidator {

    public static void validate(Response response) {
        HttpStatus statusCode = HttpStatus.valueOf(response.getStatus());
        if (statusCode.value() < 400) {
            return;
        }

        ErrorRepresentation error = response.readEntity(ErrorRepresentation.class);
        String errorMessage = error.getErrorMessage();

        String responseMessage = switch (statusCode) {
            case CONFLICT -> handleConflict(errorMessage);
            case UNAUTHORIZED -> handleUnauthorized(errorMessage);
            case BAD_REQUEST -> handleBadRequest(errorMessage);
            default -> throw new InternalServerErrorException(
                    buildExceptionMessage(errorMessage, statusCode));
        };

        throwExceptionForStatus(statusCode, responseMessage);
    }

    private static String handleConflict(String errorMessage) {
        return switch (errorMessage) {
            case USER_WITH_SAME_EMAIL_EXISTS -> ApplicationMessages.REGISTER_USER_WITH_USED_EMAIL;
            case USER_WITH_SAME_USERNAME_EXISTS -> ApplicationMessages.REGISTER_USER_WITH_USED_PHONE;
            default -> throw new InternalServerErrorException(
                    buildExceptionMessage(errorMessage, HttpStatus.CONFLICT));
        };
    }

    private static String handleUnauthorized(String errorMessage) {
        return switch (errorMessage) {
            case INVALID_REFRESH_TOKEN -> ApplicationMessages.INVALID_REFRESH_TOKEN;
            default -> throw new InternalServerErrorException(
                    buildExceptionMessage(errorMessage, HttpStatus.UNAUTHORIZED));
        };
    }

    private static String handleBadRequest(String errorMessage) {
        return switch (errorMessage) {
            case INVALID_REFRESH_TOKEN -> ApplicationMessages.INVALID_REFRESH_TOKEN;
            case REFRESH_TOKEN_EXPIRED -> ApplicationMessages.REFRESH_TOKEN_EXPIRED;
            default -> throw new InternalServerErrorException(
                    buildExceptionMessage(errorMessage, HttpStatus.BAD_REQUEST));
        };
    }

    private static void throwExceptionForStatus(HttpStatus status, String message) {
        switch (status) {
            case CONFLICT -> throw new ConflictException(message);
            case UNAUTHORIZED -> throw new UnauthorizedException(message);
            case BAD_REQUEST -> throw new BadRequestException(message);
            default -> throw new InternalServerErrorException(
                    buildExceptionMessage(message, status));
        }
    }

    private static String buildExceptionMessage(String message, HttpStatus status) {
        return String.format("Keycloak error: status: %d, response message: %s", status.value(), message);
    }
}
