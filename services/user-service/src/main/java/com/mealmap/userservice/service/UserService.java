package com.mealmap.userservice.service;

import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.userservice.core.dto.filter.UserFilter;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.entity.enums.UserRole;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface UserService {
    PageDto<UserDto> getPageOfUsers(
            Integer offset, Integer limit, UserSortField sortBy, Sort.Direction sortOrder,
            UserFilter filter, String name);

    UserDto getUser(UUID id);

    UserDto updateUser(UUID id, UserUpdatingDto userDto);

    UserDto updateUserRole(UUID id, UserRole role);

    PageDto<StatusHistoryDto> getUserStatusHistory(
            UUID id, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilter filter);

    StatusHistoryDto activateUser(UUID id, StatusHistoryCreationDto statusDto);

    StatusHistoryDto deactivateUser(UUID id, StatusHistoryCreationDto statusDto);

    StatusHistoryDto blockUser(UUID id, StatusHistoryCreationDto statusDto);

    StatusHistoryDto temporaryBlockUser(UUID id, StatusHistoryCreationDto statusDto);

    StatusHistoryDto unblockUser(UUID id, StatusHistoryCreationDto statusDto);
}
