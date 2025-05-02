package com.mealmap.userservice.service.impl;

import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import com.mealmap.starters.securitystarter.security.util.AuthenticationUtil;
import com.mealmap.userservice.core.dto.filter.UserStatusHistoryFilter;
import com.mealmap.userservice.core.dto.history.StatusHistoryCreationDto;
import com.mealmap.userservice.core.dto.history.StatusHistoryDto;
import com.mealmap.userservice.core.enums.sort.StatusHistorySortField;
import com.mealmap.userservice.core.mapper.UserStatusHistoryMapper;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.UserStatusHistory;
import com.mealmap.userservice.entity.enums.StatusEvent;
import com.mealmap.userservice.kafka.mapper.UserKafkaMapper;
import com.mealmap.userservice.repository.UserRepository;
import com.mealmap.userservice.repository.UserStatusHistoryRepository;
import com.mealmap.userservice.service.UserKafkaService;
import com.mealmap.userservice.service.UserStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.mealmap.userservice.core.message.ApplicationMessages.USER_NOT_FOUND;
import static com.mealmap.userservice.entity.specification.UserStatusHistorySpecification.*;

@Service
@RequiredArgsConstructor
public class UserStatusHistoryServiceImpl implements UserStatusHistoryService {
    private final UserStatusHistoryMapper statusHistoryMapper;

    private final UserStatusHistoryRepository statusHistoryRepository;

    private final UserRepository userRepository;

    private final UserKafkaMapper userKafkaMapper;

    private final UserKafkaService userKafkaService;

    @Override
    @Transactional
    public StatusHistoryDto createUserStatusHistoryElement(
            StatusEvent status, User user, StatusHistoryCreationDto historyDto) {

        UserStatusHistory newHistoryElement = statusHistoryMapper.dtoToEntity(historyDto);
        newHistoryElement.setUser(user);
        newHistoryElement.setNewStatus(status);
        setChangedBy(newHistoryElement);

        userKafkaService.updateUserStatus(
                userKafkaMapper.entityToStatusUpdateDto(user));

        return statusHistoryMapper.entityToDto(
                statusHistoryRepository.save(newHistoryElement));
    }

    @Override
    public Page<StatusHistoryDto> getUserStatusHistory(
            User user, Integer offset, Integer limit, StatusHistorySortField sortBy, Sort.Direction sortOrder,
            UserStatusHistoryFilter filter) {

        PageRequest pageRequest = PageBuilder.buildPageRequest(
                offset, limit, sortBy.getValue(), sortOrder);

        Specification<UserStatusHistory> spec = Specification
                .where(withUser(user))
                .and(withStatusEvent(filter.getStatusEvent()))
                .and(createdAtAfter(filter.getStartTime()))
                .and(createdAtBefore(filter.getEndTime()));

        return statusHistoryMapper.entityPageToDtoPage(
                statusHistoryRepository.findAll(spec, pageRequest));
    }

    private void setChangedBy(UserStatusHistory userStatusHistory) {
        var userId = AuthenticationUtil.getUserId();

        User changedBy = getUserEntity(userId);

        userStatusHistory.setChangedBy(changedBy);
    }

    private User getUserEntity(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND.formatted(id)));
    }
}
