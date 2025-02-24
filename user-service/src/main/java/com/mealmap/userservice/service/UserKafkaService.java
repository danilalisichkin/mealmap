package com.mealmap.userservice.service;

import com.mealmap.userservice.kafka.dto.KafkaUserCreationDto;
import com.mealmap.userservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.userservice.kafka.dto.KafkaUserUpdateDto;

public interface UserKafkaService {
    void createUser(KafkaUserCreationDto userDto);

    void updateUser(KafkaUserUpdateDto userDto);

    void updateUserRole(KafkaUserRoleUpdateDto userDto);

    void updateUserStatus(KafkaUserStatusUpdateDto userDto);
}
