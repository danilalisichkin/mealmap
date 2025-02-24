package com.mealmap.userservice.service.impl;

import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserUpdateDto;
import com.mealmap.userservice.kafka.mapper.UserKafkaMapper;
import com.mealmap.userservice.kafka.producer.UserRoleUpdateProducer;
import com.mealmap.userservice.kafka.producer.UserStatusUpdateProducer;
import com.mealmap.userservice.kafka.producer.UserUpdateProducer;
import com.mealmap.userservice.repository.UserRepository;
import com.mealmap.userservice.service.UserKafkaService;
import com.mealmap.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserKafkaServiceImpl implements UserKafkaService {
    private final UserUpdateProducer userUpdateProducer;

    private final UserRoleUpdateProducer userRoleUpdateProducer;

    private final UserStatusUpdateProducer userStatusUpdateProducer;

    private final UserValidator userValidator;

    private final UserKafkaMapper userKafkaMapper;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(KafkaUserCreationDto userDto) {
        userValidator.validateIdUniqueness(userDto.getId());
        userValidator.validatePhoneNumberUniqueness(userDto.getPhoneNumber());
        userValidator.validateEmailUniqueness(userDto.getEmail());

        User userToCreate = userKafkaMapper.creationDtoToEntity(userDto);

        userRepository.save(userToCreate);
    }

    @Override
    public void updateUser(KafkaUserUpdateDto userDto) {
        userUpdateProducer.sendMessage(userDto);
    }

    @Override
    public void updateUserRole(KafkaUserRoleUpdateDto userDto) {
        userRoleUpdateProducer.sendMessage(userDto);
    }

    @Override
    public void updateUserStatus(KafkaUserStatusUpdateDto userDto) {
        userStatusUpdateProducer.sendMessage(userDto);
    }
}
