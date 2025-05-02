package com.mealmap.userservice.service.impl;

import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import com.mealmap.userservice.core.dto.filter.UserFilter;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.enums.sort.UserSortField;
import com.mealmap.userservice.core.mapper.UserMapper;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.enums.Role;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.userservice.kafka.mapper.UserContactsKafkaMapper;
import com.mealmap.userservice.kafka.mapper.UserKafkaMapper;
import com.mealmap.userservice.repository.UserRepository;
import com.mealmap.userservice.service.UserContactsKafkaService;
import com.mealmap.userservice.service.UserKafkaService;
import com.mealmap.userservice.service.UserService;
import com.mealmap.userservice.service.UserStatusHistoryService;
import com.mealmap.userservice.strategy.manager.UserStatusChangingManager;
import com.mealmap.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.mealmap.userservice.core.message.ApplicationMessages.ROLE_IS_ALREADY_ASSIGNED;
import static com.mealmap.userservice.core.message.ApplicationMessages.ROLE_IS_NOT_ASSIGNED_YET;
import static com.mealmap.userservice.core.message.ApplicationMessages.USER_NOT_FOUND;
import static com.mealmap.userservice.entity.specification.UserSpecification.hasFirstNameLike;
import static com.mealmap.userservice.entity.specification.UserSpecification.hasLastNameLike;
import static com.mealmap.userservice.entity.specification.UserSpecification.isActive;
import static com.mealmap.userservice.entity.specification.UserSpecification.isBlocked;
import static com.mealmap.userservice.entity.specification.UserSpecification.isTemporaryBlocked;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "userProfileCacheResolver")
public class UserServiceImpl implements UserService {
    private final UserContactsKafkaService userContactsKafkaService;

    private final UserContactsKafkaMapper userContactsKafkaMapper;

    private final UserKafkaService userKafkaService;

    private final UserKafkaMapper userKafkaMapper;

    private final UserStatusChangingManager statusChangingManager;

    private final UserStatusHistoryService statusHistoryService;

    private final UserValidator userValidator;

    private final UserMapper userMapper;

    private final PageMapper pageMapper;

    private final UserRepository userRepository;

    @Override
    @Cacheable
    public PageDto<UserDto> getPageOfUsers(
            Integer offset, Integer limit, UserSortField sortBy, Sort.Direction sortOrder,
            UserFilter filter, String name) {

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
    @Cacheable(key = "#id")
    public UserDto getUser(UUID id) {
        return userMapper.entityToDto(
                getUserEntity(id));
    }

    @Override
    @Transactional
    @CachePut(key = "#id")
    public UserDto updateUser(UUID id, UserUpdatingDto userDto) {
        User userToUpdate = getUserEntity(id);

        boolean isUpdatingEmail = !userToUpdate.getEmail().equals(userDto.getEmail());
        if (isUpdatingEmail) {
            userValidator.validateEmailUniqueness(userDto.getEmail());
        }

        userMapper.updateEntityFromDto(userToUpdate, userDto);

        userRepository.save(userToUpdate);

        userKafkaService.updateUser(
                userKafkaMapper.entityToUpdateDto(userToUpdate));

        if (isUpdatingEmail) {
            userContactsKafkaService.updateUserContacts(
                    userContactsKafkaMapper.userToContactsUpdateDto(userToUpdate));
        }

        return userMapper.entityToDto(userToUpdate);
    }

    @Override
    @CachePut(key = "#id")
    public UserDto assignRole(UUID id, Role role) {
        User userToUpdate = getUserEntity(id);

        boolean isAlreadyAssigned = userToUpdate.getRoles().stream()
                .filter(ur -> ur.getRole().equals(role))
                .findFirst()
                .isEmpty();

        if (isAlreadyAssigned) {
            userKafkaService.updateUserRole(
                    new KafkaUserRoleUpdateDto(id, true, role.name()));

            return userMapper.entityToDto(
                    userRepository.save(userToUpdate));
        } else {
            throw new ConflictException(ROLE_IS_ALREADY_ASSIGNED);
        }
    }

    @Override
    @CachePut(key = "#id")
    public UserDto unassignRole(UUID id, Role role) {
        User userToUpdate = getUserEntity(id);

        boolean isRoleExistedAndRemoved = userToUpdate.getRoles()
                .removeIf(ur -> ur.getRole().equals(role));

        if (isRoleExistedAndRemoved) {
            userKafkaService.updateUserRole(
                    new KafkaUserRoleUpdateDto(id, false, role.name()));

            return userMapper.entityToDto(
                    userRepository.save(userToUpdate));
        } else {
            throw new BadRequestException(ROLE_IS_NOT_ASSIGNED_YET);
        }
    }

    @Override
    public PageDto<StatusHistoryDto> getUserStatusHistory(
            UUID id, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilter filter) {

        User requestedUser = getUserEntity(id);

        return pageMapper.pageToPageDto(
                statusHistoryService.getUserStatusHistory(
                        requestedUser, offset, limit, sortBy, sortOrder, filter));
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public StatusHistoryDto activateUser(UUID id, StatusHistoryCreationDto statusDto) {
        User userToUpdate = getUserEntity(id);

        return statusChangingManager.processStatusChange(StatusEvent.ACTIVATE, userToUpdate, statusDto);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public StatusHistoryDto deactivateUser(UUID id, StatusHistoryCreationDto statusDto) {
        User userToUpdate = getUserEntity(id);

        return statusChangingManager.processStatusChange(StatusEvent.DEACTIVATE, userToUpdate, statusDto);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public StatusHistoryDto blockUser(UUID id, StatusHistoryCreationDto statusDto) {
        User userToUpdate = getUserEntity(id);

        return statusChangingManager.processStatusChange(StatusEvent.BLOCK, userToUpdate, statusDto);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public StatusHistoryDto temporaryBlockUser(UUID id, StatusHistoryCreationDto statusDto) {
        User userToUpdate = getUserEntity(id);

        return statusChangingManager.processStatusChange(StatusEvent.TEMPORARY_BLOCK, userToUpdate, statusDto);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public StatusHistoryDto unblockUser(UUID id, StatusHistoryCreationDto statusDto) {
        User userToUpdate = getUserEntity(id);

        return statusChangingManager.processStatusChange(StatusEvent.UNBLOCK, userToUpdate, statusDto);
    }

    private User getUserEntity(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND.formatted(id)));
    }
}
