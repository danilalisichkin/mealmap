package com.mealmap.cartservice.service;

import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;

public interface CartKafkaService {
    void createCart(KafkaCartCreationDto cartDto);
}
