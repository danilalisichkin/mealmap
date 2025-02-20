package com.mealmap.userservice.service;

import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilterDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserStatusHistoryService {
    StatusHistoryDto processStatusChanging(User user, String reason, StatusEvent status);

    Page<StatusHistoryDto> getUserStatusHistory(
            User user, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilterDto filter);
}
