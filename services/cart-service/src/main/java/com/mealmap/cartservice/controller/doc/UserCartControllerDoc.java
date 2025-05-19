package com.mealmap.cartservice.controller.doc;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "User Cart API Controller", description = "Предоставляет функционал для работы с корзиной покупок пользователя")
public interface UserCartControllerDoc {
    @Operation(
            summary = "Get cart",
            description = "Позволяет получить корзину покупок пользователя")
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
                    description = "Доступ запрещен: пользователь не является владельцем корзины"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: корзина пользователя не найдена")
    })
    ResponseEntity<CartDto> getCart(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Add item to cart",
            description = "Позволяет добавить продукт в корзину")
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
                    description = "Доступ запрещен: пользователь не является владельцем корзины"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: корзина пользователя не найдена")
    })
    ResponseEntity<CartDto> addItemToCart(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Данные о добавляемом продукте",
                    description = "Данные, необходимые для добавления продукта в корзину",
                    required = true)
            @RequestBody @Valid CartItemAddingDto itemDto);

    @Operation(
            summary = "Change cart item quantity",
            description = "Позволяет изменить количество продуктов в позиции корзины")
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
                    description = "Доступ запрещен: пользователь не является владельцем корзины"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: корзина пользователя или ее позиция не найдены")
    })
    ResponseEntity<CartItemDto> changeCartItemQuantity(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    description = "Идентификатор позиции")
            @PathVariable Long itemId,
            @Parameter(
                    name = "Новое количество продукта",
                    description = "Новое количество продукта в корзине",
                    required = true)
            @RequestBody @NotNull @Positive @Max(20) Integer quantity);

    @Operation(
            summary = "Delete cart item",
            description = "Позволяет удалить позицию из корзины")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является владельцем корзины"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: корзина пользователя или ее позиция не найдены")
    })
    ResponseEntity<Void> deleteItemFromCart(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    description = "Идентификатор позиции")
            @PathVariable Long itemId);

    @Operation(
            summary = "Clear cart",
            description = "Позволяет очистить корзину")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является владельцем корзины"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: корзина пользователя или ее позиция не найдены")
    })
    ResponseEntity<Void> clearCart(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);
}
