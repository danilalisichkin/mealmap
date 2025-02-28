package com.mealmap.cartservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;
import com.mealmap.cartservice.service.CartKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserCreationConsumer {
    private final CartKafkaService cartKafkaService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${business.kafka.topics.cart-creation.name}",
            groupId = "${business.kafka.topics.cart-creation.consumer.group-id}")
    public void consume(Map<String, Object> message) {
        var dto = objectMapper.convertValue(message, KafkaCartCreationDto.class);

        cartKafkaService.createCart(dto);
    }
}
