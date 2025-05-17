package com.mealmap.authservice.security.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "security.web")
public class WebSecurityProperties {
    private final Cors cors;

    @Getter
    @AllArgsConstructor
    public static class Cors {
        private final List<String> allowedOrigins;
    }
}
