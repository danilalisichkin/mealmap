package com.mealmap.userservice.service.impl;

import com.mealmap.userservice.kafka.dto.KafkaCartCreationDto;
import com.mealmap.userservice.kafka.producer.CartCreationProducer;
import com.mealmap.userservice.service.CartKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartKafkaServiceImpl implements CartKafkaService {
    private final CartCreationProducer cartCreationProducer;

    @Override
    public void createCart(KafkaCartCreationDto cartDto) {
        cartCreationProducer.sendMessage(cartDto);
    }
}
