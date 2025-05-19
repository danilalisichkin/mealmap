package com.example.fileservice.controller.doc;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@SecurityRequirement(name = "token")
@Tag(name = "File API Controller", description = "Предоставляет функционал для работы с мультимедийными файлами")
public interface FileControllerDoc {
    @Operation(
            summary = "Upload file",
            description = "Позволяет загрузить файл в систему")
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
                    description = "Доступ запрещен: пользователь не является администратором")
    })
    ResponseEntity<Void> upload(
            @Parameter(
                    description = "Имя файла")
            @PathVariable("filename") @NotBlank @Size(min = 3, max = 200) String filename,
            @Parameter(
                    name = "Загружаемый файл",
                    description = "Загружаемый файл",
                    required = true)
            @RequestParam("file") MultipartFile file);

    @Operation(
            summary = "Download file",
            description = "Позволяет получить файл")
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
                    responseCode = "404",
                    description = "Ресурс не найден: файл не найден")
    })
    ResponseEntity<byte[]> download(
            @Parameter(
                    description = "Имя файла")
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename);

    @Operation(
            summary = "Delete file",
            description = "Позволяет удалить файл")
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
                    description = "Ресурс не найден: файл не найден")
    })
    ResponseEntity<Void> delete(
            @Parameter(
                    description = "Имя файла")
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename);
}
