package com.mealmap.userservice.service;

import com.mealmap.userservice.core.dto.filter.UserFilterDto;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilterDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreatingDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.page.PageDto;
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
            UserFilterDto filter, String name);

    UserDto getUser(UUID id);

    UserDto updateUser(UUID id, UserUpdatingDto userDto);

    UserDto updateUserRole(UUID id, UserRole role);

    PageDto<StatusHistoryDto> getUserStatusHistory(
            UUID id, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilterDto filter);

    StatusHistoryDto activateUser(UUID id, StatusHistoryCreatingDto statusDto);

    StatusHistoryDto deactivateUser(UUID id, StatusHistoryCreatingDto statusDto);

    StatusHistoryDto blockUser(UUID id, StatusHistoryCreatingDto statusDto);

    StatusHistoryDto temporaryBlockUser(UUID id, StatusHistoryCreatingDto statusDto);

    StatusHistoryDto unblockUser(UUID id, StatusHistoryCreatingDto statusDto);
}
