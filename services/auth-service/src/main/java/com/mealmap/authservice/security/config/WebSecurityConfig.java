package com.mealmap.authservice.security.config;

import com.mealmap.authservice.security.properties.WebSecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static com.mealmap.authservice.security.util.RequestMatcherMapper.toAntRequestMatchers;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final WebSecurityProperties webSecurityProperties;

    public WebSecurityConfig(WebSecurityProperties webSecurityProperties) {
        this.webSecurityProperties = webSecurityProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> actuatorEndpoints = webSecurityProperties.getOpenEndpoints().getActuator();
        List<String> openApiEndpoints = webSecurityProperties.getOpenEndpoints().getOpenApi();

        return http
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(c -> c
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers(
                                toAntRequestMatchers(actuatorEndpoints))
                        .permitAll()
                        .requestMatchers(
                                toAntRequestMatchers(openApiEndpoints))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(webSecurityProperties.getCors().getAllowedOrigins());
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
