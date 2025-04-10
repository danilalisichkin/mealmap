package com.mealmap.starters.securitystarter.autoconfigure;

import com.mealmap.starters.securitystarter.security.config.OAuth2ClientConfig;
import com.mealmap.starters.securitystarter.security.config.WebSecurityConfig;
import com.mealmap.starters.securitystarter.security.expression.config.ExpressionConfig;
import com.mealmap.starters.securitystarter.security.filter.UserAuthorizationFilter;
import com.mealmap.starters.securitystarter.security.properties.OAuth2ClientProperties;
import com.mealmap.starters.securitystarter.security.service.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@AutoConfiguration
@ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({OAuth2ResourceServerProperties.class, OAuth2ClientProperties.class})
@Import({WebSecurityConfig.class, OAuth2ClientConfig.class, ExpressionConfig.class})
public class SecurityAutoConfigure {
    @Bean
    @ConditionalOnMissingBean
    public OAuth2ResourceServerProperties oAuth2ResourceServerProperties() {
        OAuth2ResourceServerProperties properties = new OAuth2ResourceServerProperties();
        properties.getJwt().setJwkSetUri("http://localhost:8080/realms/mealmap-realm/protocol/openid-connect/certs");
        properties.getJwt().setIssuerUri("http://localhost:8080/realms/mealmap-realm");
        return properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenProvider tokenProvider(
            @Value("${spring.application.name}") String registrationId,
            OAuth2ClientProperties oAuth2ClientProperties,
            OAuth2AuthorizedClientManager authorizedClientManager) {
        return new TokenProvider(registrationId, oAuth2ClientProperties.getClientId(), authorizedClientManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserAuthorizationFilter userAuthorizationFilter() {
        return new UserAuthorizationFilter();
    }
}
