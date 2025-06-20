package com.mealmap.cartservice.service.impl;

import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.kafka.dto.KafkaCartCreationDto;
import com.mealmap.cartservice.kafka.mapper.CartKafkaMapper;
import com.mealmap.cartservice.repository.CartRepository;
import com.mealmap.cartservice.service.UserCartKafkaService;
import com.mealmap.cartservice.validator.CartValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCartKafkaServiceImpl implements UserCartKafkaService {
    private final CartValidator cartValidator;

    private final CartRepository cartRepository;

    private final CartKafkaMapper cartKafkaMapper;

    @Override
    @Transactional
    public void createCart(KafkaCartCreationDto cartDto) {
        cartValidator.validateUserIdUniqueness(cartDto.getUserId());

        Cart cartToCreate = cartKafkaMapper.dtoToEntity(cartDto);

        cartRepository.save(cartToCreate);
    }
}
