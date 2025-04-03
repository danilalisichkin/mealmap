package com.mealmap.notificationservice.client;

import com.mealmap.notificationservice.client.config.TelegramBotApiClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "telegram-bot-api",
        url = "${business.services.telegram-bot.url}",
        contextId = "productClient",
        configuration = TelegramBotApiClientConfig.class)
public interface TelegramBotApiClient {
    @PostMapping("/chats/{chatId}/message")
    void sendMessageToChat(@PathVariable Long chatId, @RequestBody String message);
}
