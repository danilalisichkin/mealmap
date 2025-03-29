package com.mealmap.notificationservice.controller.api;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.dto.page.PageDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    @GetMapping()
    public ResponseEntity<PageDto<NotificationDto>> getPageOfNotifications(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "ID") NotificationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute NotificationFilter filter,
            @RequestParam(required = false) String search) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
