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

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_IS_ALREADY_BLOCKED;

@Component
@RequiredArgsConstructor
public class BlockUserHandler implements UserStatusChangingHandler {
    private final UserStatusHistoryService statusHistoryService;

    @Override
    public StatusHistoryDto handle(User user, StatusHistoryCreationDto statusDto) {
        boolean isAlreadyBlocked = user.getStatus().getIsBlocked();

        if (isAlreadyBlocked) {
            throw new BadRequestException(USER_IS_ALREADY_BLOCKED);
        } else {
            user.getStatus().setIsBlocked(true);
        }

        return statusHistoryService.createUserStatusHistoryElement(getSupportedEvent(), user, statusDto);
    }

    @Override
    public StatusEvent getSupportedEvent() {
        return StatusEvent.BLOCK;
    }
}
