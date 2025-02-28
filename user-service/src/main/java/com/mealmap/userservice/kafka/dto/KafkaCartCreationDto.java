package com.mealmap.userservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaCartCreationDto {
    private UUID id;
}
