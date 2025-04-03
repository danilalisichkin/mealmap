package com.mealmap.telegrambot.controller.api;

import com.mealmap.telegrambot.service.TgLinkService;
import com.mealmap.telegrambot.service.TgNotificationService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telegram-bot")
public class TgBotController {
    private final TgNotificationService tgNotificationService;

    private final TgLinkService tgLinkService;

    @PostMapping("/chats/{chatId}/message")
    public ResponseEntity<Void> sendMessageToChat(
            @PathVariable Long chatId,
            @RequestBody @NotBlank @Size(max = 4096) String message) {

        tgNotificationService.sendMessageToChat(chatId, message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/links/start")
    public ResponseEntity<String> generateStartLink(@RequestParam UUID userId) {
        String link = tgLinkService.generateStartLinkForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(link);
    }
}
