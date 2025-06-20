package com.mealmap.notificationservice.controller.api;

import com.mealmap.notificationservice.controller.doc.UserNotificationControllerDoc;
import com.mealmap.notificationservice.core.dto.contacts.UserContactsDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.notificationservice.service.UserNotificationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserNotificationController implements UserNotificationControllerDoc {
    private final UserNotificationService userNotificationService;

    @Override
    @GetMapping("/{userId}/notifications")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<PageDto<NotificationDto>> getPageOfNotifications(
            @PathVariable @UUID String userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") NotificationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<NotificationDto> page =
                userNotificationService.getPageOfNotifications(userId, offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/{userId}/contacts")
    @PreAuthorize("hasUserId(#userId) or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<UserContactsDto> getContacts(@PathVariable @UUID String userId) {
        UserContactsDto contacts = userNotificationService.getContacts(userId);

        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    @PostMapping("/{userId}/notifications")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<NotificationDto> createNotification(
            @PathVariable @UUID String userId,
            @RequestBody @Valid NotificationCreationDto notificationDto) {

        NotificationDto notification = userNotificationService.createNotification(userId, notificationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
