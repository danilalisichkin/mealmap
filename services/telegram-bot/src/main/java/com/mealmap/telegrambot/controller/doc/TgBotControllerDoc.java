package com.mealmap.telegrambot.controller.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "Telegram Bot API Controller", description = "Предоставляет функционал для работы с Telegram-ботом")
public interface TgBotControllerDoc {

    @Operation(
            summary = "Send message",
            description = "Позволяет отправить сообщение в Telegram-чат с пользователем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа")
    })
    ResponseEntity<Void> sendMessageToChat(
            @Parameter(
                    name = "Идентификатор Telegram-чата")
            @PathVariable Long chatId,
            @Parameter(
                    name = "Содержание сообщения",
                    description = "Содержание сообщения, отправляемого пользователю",
                    required = true)
            @RequestBody @NotBlank @Size(max = 4096) String message);

    @Operation(
            summary = "Generate write link",
            description = "Позволяет получить ссылку на чат с Telegram-ботом")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа")
    })
    ResponseEntity<String> generateWriteLink();

    @Operation(
            summary = "Generate start link",
            description = "Позволяет получить ссылку на старт чата с Telegram-ботом")
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
                    description = "Отказ в доступе: пользователь пытался получить ссылку другого пользователя")
    })
    ResponseEntity<String> generateStartLink(
            @Parameter(
                    name = "Идентификатор пользователя")
            @RequestParam UUID userId);
}
