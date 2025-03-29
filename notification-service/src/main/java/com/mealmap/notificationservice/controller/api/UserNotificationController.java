package com.mealmap.notificationservice.controller.api;

import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.dto.page.PageDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.checkerframework.checker.index.qual.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserNotificationController {
    @GetMapping("/{userId}/notifications")
    public ResponseEntity<PageDto<NotificationDto>> getPageOfNotifications(
            @PathVariable @UUID String userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") NotificationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{userId}/notifications")
    public ResponseEntity<NotificationDto> createNotification(
            @PathVariable @UUID String userId,
            @RequestBody @Valid NotificationCreationDto notificationDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
