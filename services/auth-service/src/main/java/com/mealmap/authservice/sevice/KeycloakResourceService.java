package com.mealmap.authservice.sevice;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Map;

public interface KeycloakResourceService {
    UserRepresentation createUser(
            String username, String email, String firstName, String lastName, String password,
            Map<String, List<String>> attributes);

    void updateUser(
            String userId, String email, String firstName, String lastName,
            Map<String, List<String>> attributes);

    AccessTokenResponse getUserAccessToken(String username, String password);

    AccessTokenResponse refreshUserAccessToken(String refreshToken);

    UserResource findUserResourceByUserId(String userId);

    void deleteUser(String userId);

    void sendVerificationEmail(String userId);
}
