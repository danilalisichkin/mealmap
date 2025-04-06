package com.mealmap.notificationservice.kafka.dto;

import com.mealmap.notificationservice.doc.enums.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaNotificationCreationDto {
    private UUID userId;
    private Channel channel;
    private String subject;
    private String message;
}