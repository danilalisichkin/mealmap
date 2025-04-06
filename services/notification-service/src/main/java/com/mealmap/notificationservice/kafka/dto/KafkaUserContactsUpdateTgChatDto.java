package com.mealmap.notificationservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserContactsUpdateTgChatDto {
    private UUID userId;
    private Long chatId;
}
