package com.mealmap.userservice.controller.doc;

import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.userservice.core.dto.filter.UserFilter;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.entity.enums.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "User API Controller", description = "Предоставляет функционал для работы с учетными записями пользователей")
public interface UserControllerDoc {

    @Operation(
            summary = "Get users",
            description = "Позволяет получить страницу пользователей")
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
                    description = "Доступ запрещен: пользователь не является оператором")
    })
    ResponseEntity<PageDto<UserDto>> getPageOfUsers(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid UserFilter filter,
            @RequestParam(required = false) String name);

    @Operation(
            summary = "Get user",
            description = "Позволяет получить информацию о пользователе")
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
                    description = "Доступ запрещен: пользователь не является оператором и пытается получить информацию о другом пользователе"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о пользователе не найдена")
    })
    ResponseEntity<UserDto> getUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id);

    @Operation(
            summary = "Update user",
            description = "Позволяет обновить информацию о пользователе")
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
                    description = "Доступ запрещен: пользователь не является оператором и пытается обновить информацию о другом пользователе"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: пользователь с указанным номером телефона или email уже существует")
    })
    ResponseEntity<UserDto> updateUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация о пользователе",
                    description = "Информация о пользователе",
                    required = true)
            @RequestBody @Valid UserUpdatingDto userDto);

    @Operation(
            summary = "Assign role",
            description = "Позволяет назначить роль пользователю")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь или роль не найдены"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: пользователю уже назначена данная роль")
    })
    ResponseEntity<UserDto> assignRole(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Роль пользователя",
                    description = "Новая роль пользователя",
                    required = true)
            @RequestBody @NotNull Role role);

    @Operation(
            summary = "Unassign role",
            description = "Позволяет снять роль с пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь или роль не найдены")
    })
    ResponseEntity<UserDto> unassignRole(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Роль пользователя",
                    description = "Снимаемая роль пользователя",
                    required = true)
            @RequestBody @NotNull Role role);

    @Operation(
            summary = "Get status history",
            description = "Позволяет получить страницу истории изменения статусап пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором и пытается получить информацию о другом пользователе"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<PageDto<StatusHistoryDto>> getUserStatusHistory(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") StatusHistorySortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid UserStatusHistoryFilter filter);

    @Operation(
            summary = "Activate user",
            description = "Позволяет активировать учетную запись пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<StatusHistoryDto> activateUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация об изменении статуса",
                    description = "Информация об изменении статуса пользователя",
                    required = true)
            @RequestBody @Valid StatusHistoryCreationDto statusDto);

    @Operation(
            summary = "Deactivate user",
            description = "Позволяет деактивировать учетную запись пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<StatusHistoryDto> deactivateUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация об изменении статуса",
                    description = "Информация об изменении статуса пользователя",
                    required = true)
            @RequestBody @Valid StatusHistoryCreationDto statusDto);

    @Operation(
            summary = "Block user",
            description = "Позволяет заблокировать учетную запись пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<StatusHistoryDto> blockUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация об изменении статуса",
                    description = "Информация об изменении статуса пользователя",
                    required = true)
            @RequestBody @Valid StatusHistoryCreationDto statusDto);

    @Operation(
            summary = "Temporary block user",
            description = "Позволяет временно заблокировать учетную запись пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<StatusHistoryDto> temporaryBlockUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация об изменении статуса",
                    description = "Информация об изменении статуса пользователя",
                    required = true)
            @RequestBody @Valid StatusHistoryCreationDto statusDto);

    @Operation(
            summary = "Unblock user",
            description = "Позволяет разблокировать учетную запись пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: пользователь не найден")
    })
    ResponseEntity<StatusHistoryDto> unblockUser(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID id,
            @Parameter(
                    name = "Информация об изменении статуса",
                    description = "Информация об изменении статуса пользователя",
                    required = true)
            @RequestBody @Valid StatusHistoryCreationDto statusDto);
}
