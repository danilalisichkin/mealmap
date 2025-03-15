package com.mealmap.preferenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PreferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreferenceServiceApplication.class, args);
    }

}
