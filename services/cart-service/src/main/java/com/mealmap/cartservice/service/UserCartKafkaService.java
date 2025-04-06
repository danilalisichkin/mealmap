package com.mealmap.cartservice.service;

import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;

public interface UserCartKafkaService {
    void createCart(KafkaCartCreationDto cartDto);
}
