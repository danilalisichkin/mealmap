package com.mealmap.userservice.strategy;

import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;

public interface UserStatusChangingHandler {
    StatusHistoryDto handle(User user, StatusHistoryCreationDto statusDto);

    StatusEvent getSupportedEvent();
}
