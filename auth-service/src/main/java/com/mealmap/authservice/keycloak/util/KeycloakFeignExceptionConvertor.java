package com.mealmap.authservice.keycloak.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.ErrorRepresentation;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeycloakFeignExceptionConvertor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Response convertToKeycloakResponse(final FeignException exception) {
        try {
            String body = exception.responseBody()
                    .map(byteBuffer -> new String(byteBuffer.array(), StandardCharsets.UTF_8))
                    .orElseThrow(() -> new InternalServerErrorException(exception.getMessage()));

            JsonNode jsonNode = objectMapper.readTree(body);
            String errorDescription = jsonNode.get("error_description").asText();

            ErrorRepresentation keycloakError = new ErrorRepresentation();
            keycloakError.setErrorMessage(errorDescription);

            return Response
                    .status(exception.status())
                    .entity(keycloakError)
                    .build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}