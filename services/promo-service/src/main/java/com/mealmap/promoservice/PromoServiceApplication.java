package com.mealmap.promoservice;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableMongock
@SpringBootApplication
@EnableDiscoveryClient
public class PromoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromoServiceApplication.class, args);
    }

}
