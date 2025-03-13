package com.mealmap.authservice.client;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "keycloak-api",
        url = "${business.keycloak.server-url}/realms/${business.keycloak.realm}/protocol/openid-connect")
public interface KeycloakFeignClient {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse refreshToken(@RequestBody Map<String, ?> formParams);
}
