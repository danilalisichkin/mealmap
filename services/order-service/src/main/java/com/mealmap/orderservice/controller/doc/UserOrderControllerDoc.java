package com.mealmap.orderservice.controller.doc;

import com.mealmap.orderservice.core.dto.order.OrderCreationDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
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
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "token")
@Tag(name = "User Order API Controller", description = "Предоставляет функционал для работы с заказами пользователей")
public interface UserOrderControllerDoc {

    @Operation(
            summary = "Get orders",
            description = "Позволяет получить страницу заказов пользователя")
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
                    description = "Доступ запрещен: пользователь не является оператором и пытается получить заказы другого пользователя")
    })
    ResponseEntity<PageDto<OrderDto>> getPageOfUserOrders(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable @UUID String userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrderSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder);

    @Operation(
            summary = "Create order",
            description = "Позволяет пользователю сделать заказ")
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
                    description = "Доступ запрещен: пользователь пытается оформить заказ для другого пользователя")
    })
    ResponseEntity<OrderDto> createOrder(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable @UUID String userId,
            @Parameter(
                    name = "Информация о заказе",
                    description = "Информация о создаваемом заказе",
                    required = true)
            @RequestBody @Valid OrderCreationDto orderDto);

    @Operation(
            summary = "Update order status",
            description = "Позволяет пользователю управлять жизненным циклом заказа, изменяя его статус")
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
                    description = "Доступ запрещен: пользователь пытается изменить статус заказа другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: заказ не найден")
    })
    ResponseEntity<OrderDto> updateOrderStatus(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable @UUID String userId,
            @Parameter(
                    name = "Идентификатор заказа")
            @PathVariable ObjectId id,
            @Parameter(
                    name = "Статус заказа",
                    description = "Новый статус заказа",
                    required = true)
            @RequestBody @NotNull OrderStatus status);
}
