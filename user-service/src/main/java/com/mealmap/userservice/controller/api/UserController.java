package com.mealmap.userservice.controller.api;

import com.mealmap.userservice.core.dto.filter.UserFilter;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.page.PageDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.entity.enums.UserRole;
import com.mealmap.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PageDto<UserDto>> getPageOfUsers(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid UserFilter filter,
            @RequestParam(required = false) String name) {

        PageDto<UserDto> page = userService.getPageOfUsers(offset, limit, sortBy, sortOrder, filter, name);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        UserDto user = userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id, @RequestBody @Valid UserUpdatingDto userDto) {

        UserDto user = userService.updateUser(id, userDto);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserDto> updateUserRole(
            @PathVariable UUID id, @RequestBody @NotNull UserRole role) {

        UserDto user = userService.updateUserRole(id, role);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}/status-history")
    public ResponseEntity<PageDto<StatusHistoryDto>> getUserStatusHistory(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") StatusHistorySortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid UserStatusHistoryFilter filter) {

        PageDto<StatusHistoryDto> page =
                userService.getUserStatusHistory(id, offset, limit, sortBy, sortOrder, filter);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<StatusHistoryDto> activateUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.activateUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<StatusHistoryDto> deactivateUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.deactivateUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @PostMapping("/{id}/block")
    public ResponseEntity<StatusHistoryDto> blockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.blockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @PostMapping("/{id}/temporary-block")
    public ResponseEntity<StatusHistoryDto> temporaryBlockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.temporaryBlockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @PostMapping("/{id}/unblock")
    public ResponseEntity<StatusHistoryDto> unblockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.unblockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
}
