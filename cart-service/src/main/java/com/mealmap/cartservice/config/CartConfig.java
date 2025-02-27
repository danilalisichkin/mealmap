package com.mealmap.cartservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.cart")
public class CartConfig {
    private final Integer maxItemQuantityInCart;
    private final Integer maxProductQuantityInCart;
}
