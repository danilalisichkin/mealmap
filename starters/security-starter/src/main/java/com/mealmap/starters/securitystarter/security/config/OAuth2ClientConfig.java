package com.mealmap.starters.securitystarter.security.config;

import com.mealmap.starters.securitystarter.security.properties.OAuth2ClientProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class OAuth2ClientConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public ClientRegistration internalClientRegistration(OAuth2ClientProperties properties) {
        if (properties.getClientId() == null || properties.getClientId().isBlank()) {
            throw new IllegalArgumentException("security.oauth2.client.client-id must be set");
        }
        if (properties.getClientSecret() == null || properties.getClientSecret().isBlank()) {
            throw new IllegalArgumentException("security.oauth2.client.client-secret must be set");
        }

        return ClientRegistration.withRegistrationId(applicationName)
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .tokenUri(properties.getTokenUri())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration registration) {
        return new InMemoryClientRegistrationRepository(registration);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository repository) {
        return new InMemoryOAuth2AuthorizedClientService(repository);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                clientRegistrationRepository,
                authorizedClientService);
    }
}
