package com.mealmap.notificationservice.client;

import com.mealmap.notificationservice.client.config.FeignOAuth2Config;
import com.mealmap.notificationservice.client.config.TelegramBotApiClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${business.services.telegram-bot.name}",
        path = "${business.services.telegram-bot.path}",
        contextId = "productClient",
        configuration = {TelegramBotApiClientConfig.class, FeignOAuth2Config.class})
public interface TelegramBotApiClient {
    @PostMapping("/chats/{chatId}/message")
    void sendMessageToChat(@PathVariable Long chatId, @RequestBody String message);
}
