package com.mealmap.starters.securitystarter.autoconfigure;

import com.mealmap.starters.securitystarter.security.config.WebSecurityConfig;
import com.mealmap.starters.securitystarter.security.filter.UserAuthorizationFilter;
import com.mealmap.starters.securitystarter.security.service.SecurityService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnClass(name = "org.springframework.security.config.annotation.web.builders.HttpSecurity")
@ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(OAuth2ResourceServerProperties.class)
@Import(WebSecurityConfig.class)
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
    public SecurityService securityService() {
        return new SecurityService();
    }

    @Bean
    @ConditionalOnMissingBean
    public UserAuthorizationFilter userAuthorizationFilter() {
        return new UserAuthorizationFilter(securityService());
    }
}
