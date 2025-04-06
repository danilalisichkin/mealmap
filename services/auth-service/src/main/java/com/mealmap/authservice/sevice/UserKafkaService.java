package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.authservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.authservice.kafka.dto.KafkaUserUpdateDto;

public interface UserKafkaService {
    void createUser(UserDto dto);

    void updateUser(KafkaUserUpdateDto kafkaUserUpdateDto);

    void updateUserRole(KafkaUserRoleUpdateDto updateDto);

    void updateUserStatus(KafkaUserStatusUpdateDto updateDto);
}
