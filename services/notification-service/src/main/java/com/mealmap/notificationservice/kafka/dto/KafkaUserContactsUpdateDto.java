package com.mealmap.notificationservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserContactsUpdateDto {
    private UUID userId;
    private String email;
}
