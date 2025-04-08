package com.mealmap.notificationservice.controller.api;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.notificationservice.service.NotificationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageDto<NotificationDto>> getPageOfNotifications(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") NotificationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid NotificationFilter filter,
            @RequestParam(required = false) @Size(max = 4000) String search) {

        PageDto<NotificationDto> page =
                notificationService.getPageOfNotifications(offset, limit, sortBy, sortOrder, filter, search);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
