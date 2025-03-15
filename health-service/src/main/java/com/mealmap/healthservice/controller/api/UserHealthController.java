package com.mealmap.healthservice.controller.api;

import com.mealmap.healthservice.core.dto.diet.UserDietCreationDto;
import com.mealmap.healthservice.core.dto.diet.UserDietDto;
import com.mealmap.healthservice.core.dto.diet.UserDietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.UserPhysicHealthUpdatingDto;
import com.mealmap.healthservice.service.UserHealthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserHealthController {
    private final UserHealthService userHealthService;

    @GetMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> getUserPhysicHealth(@PathVariable UUID userId) {
        UserPhysicHealthDto physicHealth = userHealthService.getUserPhysicHealth(userId);

        return ResponseEntity.status(HttpStatus.OK).body(physicHealth);
    }

    @GetMapping("/{userId}/physic-health/history")
    public ResponseEntity<List<UserPhysicHealthHistoryDto>> getUserPhysicHealthHistory(@PathVariable UUID userId) {
        List<UserPhysicHealthHistoryDto> healthHistory = userHealthService.getUserPhysicHealthHistory(userId);

        return ResponseEntity.status(HttpStatus.OK).body(healthHistory);
    }

    @GetMapping("/{userId}/diet")
    public ResponseEntity<UserDietDto> getUserDiet(@PathVariable UUID userId) {
        UserDietDto userDiet = userHealthService.getUserDiet(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDiet);
    }

    @PostMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> createUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody @Valid UserPhysicHealthCreationDto userPhysicHealthDto) {

        UserPhysicHealthDto physicHealth = userHealthService.createUserPhysicHealth(userId, userPhysicHealthDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(physicHealth);
    }

    @PostMapping("/{userId}/diet")
    public ResponseEntity<UserDietDto> createUserDiet(
            @PathVariable UUID userId,
            @RequestBody @Valid UserDietCreationDto userDietDto) {

        UserDietDto userDiet = userHealthService.createUserDiet(userId, userDietDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDiet);
    }

    @PutMapping("/{userId}/physic-health")
    public ResponseEntity<UserPhysicHealthDto> updateUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody @Valid UserPhysicHealthUpdatingDto userPhysicHealthDto) {

        UserPhysicHealthDto physicHealth = userHealthService.updateUserPhysicHealth(userId, userPhysicHealthDto);

        return ResponseEntity.status(HttpStatus.OK).body(physicHealth);
    }

    @PutMapping("/{userId}/diet")
    public ResponseEntity<UserDietDto> updateUserDiet(
            @PathVariable UUID userId,
            @RequestBody @Valid UserDietUpdatingDto userDietDto) {

        UserDietDto userDiet = userHealthService.updateUserDiet(userId, userDietDto);

        return ResponseEntity.status(HttpStatus.OK).body(userDiet);
    }

    @DeleteMapping("/{userId}/diet")
    public ResponseEntity<Void> deleteUserDiet(@PathVariable UUID userId) {
        userHealthService.deleteUserDiet(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
