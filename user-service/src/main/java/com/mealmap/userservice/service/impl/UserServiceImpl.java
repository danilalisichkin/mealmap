package com.mealmap.userservice.service.impl;

import com.mealmap.userservice.core.dto.filter.UserFilterDto;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilterDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreatingDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.page.PageDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.core.mapper.PageMapper;
import com.mealmap.userservice.core.mapper.UserMapper;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.entity.enums.UserRole;
import com.mealmap.userservice.exception.ResourceNotFoundException;
import com.mealmap.userservice.repository.UserRepository;
import com.mealmap.userservice.service.UserService;
import com.mealmap.userservice.service.UserStatusHistoryService;
import com.mealmap.userservice.util.PageBuilder;
import com.mealmap.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_NOT_FOUND;
import static com.mealmap.userservice.entity.specification.UserSpecification.hasFirstNameLike;
import static com.mealmap.userservice.entity.specification.UserSpecification.hasLastNameLike;
import static com.mealmap.userservice.entity.specification.UserSpecification.isActive;
import static com.mealmap.userservice.entity.specification.UserSpecification.isBlocked;
import static com.mealmap.userservice.entity.specification.UserSpecification.isTemporaryBlocked;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStatusHistoryService statusHistoryService;

    private final UserValidator userValidator;

    private final UserMapper userMapper;

    private final PageMapper pageMapper;

    private final UserRepository userRepository;

    @Override
    public PageDto<UserDto> getPageOfUsers(
            Integer offset, Integer limit, UserSortField sortBy, Sort.Direction sortOrder,
            UserFilterDto filter, String name) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Specification<User> spec = Specification
                .where(hasFirstNameLike(name))
                .or(hasLastNameLike(name))
                .and(isActive(filter.getIsActive()))
                .and(isTemporaryBlocked(filter.getIsTemporaryBlocked()))
                .and(isBlocked(filter.getIsBlocked()));

        return pageMapper.pageToPageDto(
                userMapper.entityPageToDtoPage(
                        userRepository.findAll(spec, request)));
    }

    @Override
    public UserDto getUser(UUID id) {
        return userMapper.entityToDto(
                getUserEntity(id));
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, UserUpdatingDto userDto) {
        User userToUpdate = getUserEntity(id);

        if (!userToUpdate.getPhoneNumber().equals(userDto.getPhoneNumber())) {
            userValidator.validatePhoneNumberUniqueness(userToUpdate.getPhoneNumber());
        }
        if (!userToUpdate.getEmail().equals(userDto.getEmail())) {
            userValidator.validateEmailUniqueness(userToUpdate.getEmail());
        }

        userMapper.updateEntityFromDto(userToUpdate, userDto);

        return userMapper.entityToDto(
                userRepository.save(userToUpdate));
    }

    @Override
    @Transactional
    public UserDto updateUserRole(UUID id, UserRole role) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.setRole(role);

        return userMapper.entityToDto(
                userRepository.save(userToUpdate));
    }

    @Override
    public PageDto<StatusHistoryDto> getUserStatusHistory(
            UUID id, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilterDto filter) {

        User userToUpdate = getUserEntity(id);

        return pageMapper.pageToPageDto(
                statusHistoryService.getUserStatusHistory(
                        userToUpdate, offset, limit, sortBy, sortOrder, filter));
    }

    @Override
    @Transactional
    public StatusHistoryDto activateUser(UUID id, StatusHistoryCreatingDto statusDto) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.getStatus().setIsActive(true);
        userRepository.save(userToUpdate);

        return statusHistoryService.processStatusChanging(
                userToUpdate, statusDto.getReason(), StatusEvent.ACTIVATE);
    }

    @Override
    @Transactional
    public StatusHistoryDto deactivateUser(UUID id, StatusHistoryCreatingDto statusDto) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.getStatus().setIsActive(false);
        userRepository.save(userToUpdate);

        return statusHistoryService.processStatusChanging(
                userToUpdate, statusDto.getReason(), StatusEvent.DEACTIVATE);
    }

    @Override
    @Transactional
    public StatusHistoryDto blockUser(UUID id, StatusHistoryCreatingDto statusDto) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.getStatus().setIsBlocked(true);
        userRepository.save(userToUpdate);

        return statusHistoryService.processStatusChanging(
                userToUpdate, statusDto.getReason(), StatusEvent.BLOCK);
    }

    @Override
    @Transactional
    public StatusHistoryDto temporaryBlockUser(UUID id, StatusHistoryCreatingDto statusDto) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.getStatus().setIsTemporaryBlocked(true);
        userRepository.save(userToUpdate);

        return statusHistoryService.processStatusChanging(
                userToUpdate, statusDto.getReason(), StatusEvent.TEMPORARY_BLOCK);
    }

    @Override
    @Transactional
    public StatusHistoryDto unblockUser(UUID id, StatusHistoryCreatingDto statusDto) {
        User userToUpdate = getUserEntity(id);

        userToUpdate.getStatus().setIsBlocked(false);
        userToUpdate.getStatus().setIsTemporaryBlocked(false);
        userRepository.save(userToUpdate);

        return statusHistoryService.processStatusChanging(
                userToUpdate, statusDto.getReason(), StatusEvent.UNBLOCK);
    }

    private User getUserEntity(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND.formatted(id)));
    }
}
