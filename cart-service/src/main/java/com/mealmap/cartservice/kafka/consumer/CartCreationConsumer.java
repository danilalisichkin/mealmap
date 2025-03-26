package com.mealmap.cartservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;
import com.mealmap.cartservice.service.UserCartKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartCreationConsumer {
    private final UserCartKafkaService userCartKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.cart-creation.name}",
            groupId = "${business.kafka.topics.cart-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaCartCreationDto.class);

        userCartKafkaService.createCart(dto);
    }
}
