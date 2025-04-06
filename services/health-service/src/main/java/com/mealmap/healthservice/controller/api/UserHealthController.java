package com.mealmap.healthservice.controller.api;

import com.mealmap.healthservice.core.dto.diet.DietCreationDto;
import com.mealmap.healthservice.core.dto.diet.DietDto;
import com.mealmap.healthservice.core.dto.diet.DietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthUpdatingDto;
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
    public ResponseEntity<PhysicHealthDto> getUserPhysicHealth(@PathVariable UUID userId) {
        PhysicHealthDto physicHealth = userHealthService.getUserPhysicHealth(userId);

        return ResponseEntity.status(HttpStatus.OK).body(physicHealth);
    }

    @GetMapping("/{userId}/physic-health/history")
    public ResponseEntity<List<PhysicHealthHistoryDto>> getUserPhysicHealthHistory(@PathVariable UUID userId) {
        List<PhysicHealthHistoryDto> healthHistory = userHealthService.getUserPhysicHealthHistory(userId);

        return ResponseEntity.status(HttpStatus.OK).body(healthHistory);
    }

    @GetMapping("/{userId}/diet")
    public ResponseEntity<DietDto> getUserDiet(@PathVariable UUID userId) {
        DietDto userDiet = userHealthService.getUserDiet(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDiet);
    }

    @PostMapping("/{userId}/physic-health")
    public ResponseEntity<PhysicHealthDto> createUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody @Valid PhysicHealthCreationDto userPhysicHealthDto) {

        PhysicHealthDto physicHealth = userHealthService.createUserPhysicHealth(userId, userPhysicHealthDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(physicHealth);
    }

    @PostMapping("/{userId}/diet")
    public ResponseEntity<DietDto> createUserDiet(
            @PathVariable UUID userId,
            @RequestBody @Valid DietCreationDto userDietDto) {

        DietDto userDiet = userHealthService.createUserDiet(userId, userDietDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDiet);
    }

    @PutMapping("/{userId}/physic-health")
    public ResponseEntity<PhysicHealthDto> updateUserPhysicHealth(
            @PathVariable UUID userId,
            @RequestBody @Valid PhysicHealthUpdatingDto userPhysicHealthDto) {

        PhysicHealthDto physicHealth = userHealthService.updateUserPhysicHealth(userId, userPhysicHealthDto);

        return ResponseEntity.status(HttpStatus.OK).body(physicHealth);
    }

    @PutMapping("/{userId}/diet")
    public ResponseEntity<DietDto> updateUserDiet(
            @PathVariable UUID userId,
            @RequestBody @Valid DietUpdatingDto userDietDto) {

        DietDto userDiet = userHealthService.updateUserDiet(userId, userDietDto);

        return ResponseEntity.status(HttpStatus.OK).body(userDiet);
    }

    @DeleteMapping("/{userId}/diet")
    public ResponseEntity<Void> deleteUserDiet(@PathVariable UUID userId) {
        userHealthService.deleteUserDiet(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
