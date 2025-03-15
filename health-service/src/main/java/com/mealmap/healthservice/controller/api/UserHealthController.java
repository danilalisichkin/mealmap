package com.mealmap.healthservice.controller.api;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserHealthController {
    @GetMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> getUserPhysicHealth(@PathVariable UUID userId) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/physic-health/history")
    public ResponseEntity<List<UserPhysicHealthDto>> getUserPhysicHealthHistory(@PathVariable UUID userId) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/diet")
    public ResponseEntity<UserDietDto> getUserDiet(@PathVariable UUID userId) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> createUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody UserPhysicHealthDto userPhysicHealthDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{userId}/diet")
    public ResponseEntity<UserDietDto> createUserDiet(
            @PathVariable UUID userId,
            @RequestBody UserDietCreationDto userDietDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> updateUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody UserPhysicHealthUpdatingDto userPhysicHealthDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{userId}/diet")
    public ResponseEntity<UserPhysicHealthDto> updateUserDiet(
            @PathVariable UUID userId,
            @RequestBody UserDietDto userPhysicHealthDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
