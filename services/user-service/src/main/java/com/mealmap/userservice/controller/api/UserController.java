package com.mealmap.userservice.controller.api;

import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.userservice.controller.doc.UserControllerDoc;
import com.mealmap.userservice.core.dto.filter.UserFilter;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.entity.enums.Role;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class UserController implements UserControllerDoc {

    private final UserService userService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
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

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasUserId(#id) or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        UserDto user = userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasUserId(#id) or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id, @RequestBody @Valid UserUpdatingDto userDto) {

        UserDto user = userService.updateUser(id, userDto);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @PostMapping("/{id}/role")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<UserDto> assignRole(
            @PathVariable UUID id, @RequestBody @NotNull Role role) {

        UserDto user = userService.assignRole(id, role);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @DeleteMapping("/{id}/role")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<UserDto> unassignRole(
            @PathVariable UUID id, @RequestBody @NotNull Role role) {

        UserDto user = userService.unassignRole(id, role);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @GetMapping("/{id}/status-history")
    @PreAuthorize("hasUserId(#id) or (hasRole('OPERATOR') and hasRole('ADMIN'))")
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

    @Override
    @PostMapping("/{id}/activate")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<StatusHistoryDto> activateUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.activateUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @Override
    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasUserId(#id) or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<StatusHistoryDto> deactivateUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.deactivateUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @Override
    @PostMapping("/{id}/block")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<StatusHistoryDto> blockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.blockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @Override
    @PostMapping("/{id}/temporary-block")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<StatusHistoryDto> temporaryBlockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.temporaryBlockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @Override
    @PostMapping("/{id}/unblock")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<StatusHistoryDto> unblockUser(
            @PathVariable UUID id, @RequestBody @Valid StatusHistoryCreationDto statusDto) {

        StatusHistoryDto history = userService.unblockUser(id, statusDto);

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
}
