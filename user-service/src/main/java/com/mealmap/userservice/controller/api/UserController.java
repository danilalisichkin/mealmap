package com.mealmap.userservice.controller.api;

import com.mealmap.userservice.core.dto.filter.UserFilterDto;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilterDto;
import com.mealmap.userservice.core.dto.page.PageDto;
import com.mealmap.userservice.core.dto.user.StatusHistoryDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public ResponseEntity<PageDto<UserDto>> getPageOfUsers(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute UserFilterDto filter,
            @RequestParam(required = false) String name) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id, @RequestBody UserUpdatingDto userDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserDto> updateUserRole(
            @PathVariable UUID id, @RequestBody UserRole role) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/status-history")
    public ResponseEntity<PageDto<StatusHistoryDto>> getUserStatusHistory(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute UserStatusHistoryFilterDto filter) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<StatusHistoryDto> activateUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<StatusHistoryDto> deactivateUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/block")
    public ResponseEntity<StatusHistoryDto> blockUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/temporary-block")
    public ResponseEntity<StatusHistoryDto> temporaryBlockUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/unblock")
    public ResponseEntity<StatusHistoryDto> unblockUser(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
