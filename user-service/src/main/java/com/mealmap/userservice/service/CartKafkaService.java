package com.mealmap.userservice.service;

import com.mealmap.userservice.kafka.dto.KafkaCartCreationDto;

public interface CartKafkaService {
    void createCart(KafkaCartCreationDto cartDto);
}
