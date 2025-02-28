package com.mealmap.userservice.strategy.manager;

import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.strategy.UserStatusChangingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserStatusChangingManager {
    private final Map<StatusEvent, UserStatusChangingHandler> handlers;

    @Autowired
    public UserStatusChangingManager(List<UserStatusChangingHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        UserStatusChangingHandler::getSupportedEvent,
                        handler -> handler));
    }

    public StatusHistoryDto processStatusChange(StatusEvent event, User user, StatusHistoryCreationDto statusDto) {
        return handlers.get(event).handle(user, statusDto);
    }
}
