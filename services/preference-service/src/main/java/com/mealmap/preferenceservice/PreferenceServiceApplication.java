package com.mealmap.preferenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ConfigurationPropertiesScan
public class PreferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreferenceServiceApplication.class, args);
    }

}
