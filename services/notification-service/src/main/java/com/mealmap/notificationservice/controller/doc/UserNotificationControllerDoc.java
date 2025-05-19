package com.mealmap.notificationservice.controller.doc;

import com.mealmap.notificationservice.core.dto.contacts.UserContactsDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationCreationDto;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.checkerframework.checker.index.qual.Positive;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "token")
@Tag(name = "User Notification API Controller", description = "Предоставляет функционал для работы с уведомлениями пользователя")
public interface UserNotificationControllerDoc {

    @Operation(
            summary = "Get notifications",
            description = "Позволяет получить страницу уведомлений пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь пытается получить уведомления другого пользователя")
    })
    ResponseEntity<PageDto<NotificationDto>> getPageOfNotifications(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable @UUID String userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") NotificationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder);

    @Operation(
            summary = "Get contacts",
            description = "Позволяет получить контактные данные пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь пытается получить контактные данные другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: контактные данные пользователя не найдены")
    })
    ResponseEntity<UserContactsDto> getContacts(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable @UUID String userId);

    @Operation(
            summary = "Create notification",
            description = "Позволяет отправить уведомление пользователю")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: контактные данные пользователя не найдены")
    })
    ResponseEntity<NotificationDto> createNotification(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable @UUID String userId,
            @Parameter(
                    name = "Информация о физическом здоровье",
                    description = "Информация о физическом здоровье пользователя",
                    required = true)
            @RequestBody @Valid NotificationCreationDto notificationDto);
}
