package com.mealmap.starters.securitystarter.security.expression.config;

import com.mealmap.starters.securitystarter.security.expression.CustomMethodSecurityExpressionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

@Configuration
public class ExpressionConfig {
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(ApplicationContext applicationContext) {
        var handler = new CustomMethodSecurityExpressionHandler();
        handler.setApplicationContext(applicationContext);

        return handler;
    }
}
