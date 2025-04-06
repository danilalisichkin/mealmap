package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.client.KeycloakFeignClient;
import com.mealmap.authservice.keycloak.config.KeycloakClientConfig;
import com.mealmap.authservice.keycloak.config.KeycloakServerConfig;
import com.mealmap.authservice.keycloak.util.KeycloakFeignExceptionConvertor;
import com.mealmap.authservice.keycloak.util.KeycloakResponseValidator;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.exceptionstarter.exception.UnauthorizedException;
import feign.FeignException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mealmap.authservice.core.message.ApplicationMessages.EMAIL_NOT_EXIST;
import static com.mealmap.authservice.core.message.ApplicationMessages.USER_WITH_ID_NOT_FOUND;
import static com.mealmap.authservice.core.message.ApplicationMessages.WRONG_LOGIN_OR_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakResourceServiceImpl implements KeycloakResourceService {
    @Value("${business.keycloak.email-service.enabled}")
    private boolean isEmailServiceEnabled;

    private final KeycloakServerConfig kcServerConfig;

    private final KeycloakClientConfig kcClientConfig;

    private final UsersResource usersResource;

    private final CredentialRepresentation credentialRepresentation;

    private final KeycloakFeignClient keycloakFeignClient;

    @Override
    public UserRepresentation createUser(
            String username, String email, String firstName, String lastName, String password,
            Map<String, List<String>> attributes) {

        UserRepresentation newUser = buildUserRepresentationForCreation(username, email, firstName, lastName, password, attributes);

        Response response = usersResource.create(newUser);
        KeycloakResponseValidator.validate(response);

        List<UserRepresentation> users = usersResource.search(username);
        UserRepresentation createdUser = users.getFirst();

        if (isEmailServiceEnabled) {
            sendVerificationEmail(createdUser.getId());
        }

        return createdUser;
    }

    @Override
    public void updateUser(
            String userId, String email, String firstName, String lastName,
            Map<String, List<String>> attributes) {

        UserRepresentation updatedUser = buildUserRepresentationForUpdate(email, firstName, lastName, attributes);

        try {
            usersResource.get(userId).update(updatedUser);
        } catch (Exception e) {
            throw new InternalServerErrorException(e);
        }
    }

    @Override
    public AccessTokenResponse getUserAccessToken(String username, String password) {
        try (Keycloak keycloakUser = buildUserClient(username, password)) {
            return keycloakUser.tokenManager().getAccessToken();
        } catch (NotAuthorizedException e) {
            throw new UnauthorizedException(WRONG_LOGIN_OR_PASSWORD);
        } catch (IllegalStateException e) {
            throw new InternalServerErrorException(e);
        }
    }

    @Override
    public AccessTokenResponse refreshUserAccessToken(String refreshToken) {
        Map<String, Object> request = new HashMap<>();
        request.put(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN);
        request.put(OAuth2Constants.CLIENT_ID, kcClientConfig.getClientId());
        request.put(OAuth2Constants.CLIENT_SECRET, kcClientConfig.getClientSecret());
        request.put(OAuth2Constants.REFRESH_TOKEN, refreshToken);

        try {
            return keycloakFeignClient.refreshToken(request);
        } catch (FeignException e) {
            Response keycloakResponse = KeycloakFeignExceptionConvertor.convertToKeycloakResponse(e);
            KeycloakResponseValidator.validate(keycloakResponse);

            return null;
        }
    }

    @Override
    public UserResource findUserResourceByUserId(String userId) {
        try {
            return usersResource.get(userId);
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(userId));
        }
    }

    @Override
    public void deleteUser(String userId) {
        Response response = usersResource.delete(userId);
        KeycloakResponseValidator.validate(response);
    }

    @Override
    public void sendVerificationEmail(String userId) {
        try {
            usersResource.get(userId).sendVerifyEmail();
        } catch (InternalServerErrorException e) {
            deleteUser(userId);
            throw new BadRequestException(EMAIL_NOT_EXIST);
        }
    }

    private Keycloak buildUserClient(String username, String password) {
        return KeycloakBuilder.builder()
                .serverUrl(kcServerConfig.getServerUrl())
                .realm(kcServerConfig.getRealm())
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(kcClientConfig.getClientId())
                .clientSecret(kcClientConfig.getClientSecret())
                .username(username)
                .password(password)
                .build();
    }

    private UserRepresentation buildUserRepresentationForCreation(
            String username, String email, String firstName, String lastName, String password,
            Map<String, List<String>> attributes) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(username);
        userRepresentation.setEmail(email);
        userRepresentation.setEmailVerified(false);
        userRepresentation.setFirstName(firstName);
        userRepresentation.setLastName(lastName);
        userRepresentation.setCreatedTimestamp(System.currentTimeMillis());
        userRepresentation.setAttributes(attributes);

        credentialRepresentation.setValue(password);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        return userRepresentation;
    }

    private UserRepresentation buildUserRepresentationForUpdate(
            String email, String firstName, String lastName,
            Map<String, List<String>> attributes) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEmail(email);
        userRepresentation.setFirstName(firstName);
        userRepresentation.setLastName(lastName);
        userRepresentation.setAttributes(attributes);

        return userRepresentation;
    }
}
