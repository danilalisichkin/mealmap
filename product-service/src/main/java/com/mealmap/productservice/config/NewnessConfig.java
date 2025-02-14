package com.mealmap.productservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.newness")
public class NewnessConfig {
    /**
     * Период в днях, в течение которого продукт считается новинкой
     */
    private final Integer period;
}
