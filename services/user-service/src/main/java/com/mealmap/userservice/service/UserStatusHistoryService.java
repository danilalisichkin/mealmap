package com.mealmap.userservice.service;

import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserStatusHistoryService {
    StatusHistoryDto createUserStatusHistoryElement(StatusEvent status, User user, StatusHistoryCreationDto historyDto);

    Page<StatusHistoryDto> getUserStatusHistory(
            User user, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilter filter);
}
