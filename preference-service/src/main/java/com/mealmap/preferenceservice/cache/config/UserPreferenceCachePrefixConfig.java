package com.mealmap.preferenceservice.cache.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "business.cache.user-preference.prefixes")
public class UserPreferenceCachePrefixConfig {
    private String productPreferences;
    private String categoryPreferences;

    @PostConstruct
    public void init() {
        log.info(this.getClass().getName() + " initialized: " + productPreferences + " " + categoryPreferences);
    }
}
