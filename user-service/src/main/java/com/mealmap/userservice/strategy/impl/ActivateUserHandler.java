package com.mealmap.userservice.strategy.impl;

import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.exception.BadRequestException;
import com.mealmap.userservice.service.UserStatusHistoryService;
import com.mealmap.userservice.strategy.UserStatusChangingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_IS_ALREADY_ACTIVE;

@Component
@RequiredArgsConstructor
public class ActivateUserHandler implements UserStatusChangingHandler {
    private final UserStatusHistoryService statusHistoryService;

    @Override
    public StatusHistoryDto handle(User user, StatusHistoryCreationDto statusDto) {
        boolean isAlreadyActive = user.getStatus().getIsActive();

        if (isAlreadyActive) {
            throw new BadRequestException(USER_IS_ALREADY_ACTIVE);
        } else {
            user.getStatus().setIsActive(true);
        }

        return statusHistoryService.createUserStatusHistoryElement(getSupportedEvent(), user, statusDto);
    }

    @Override
    public StatusEvent getSupportedEvent() {
        return StatusEvent.ACTIVATE;
    }
}
