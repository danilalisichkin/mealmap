package com.mealmap.starters.securitystarter.security.common.expression.config;

import com.mealmap.starters.securitystarter.security.common.expression.CustomMethodSecurityExpressionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

@Configuration
public class SecurityExpressionConfig {
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(ApplicationContext applicationContext) {
        var handler = new CustomMethodSecurityExpressionHandler();
        handler.setApplicationContext(applicationContext);

        return handler;
    }
}
