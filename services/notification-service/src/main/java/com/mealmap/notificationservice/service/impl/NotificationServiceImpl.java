package com.mealmap.notificationservice.service.impl;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.mealmap.notificationservice.core.dto.notification.NotificationDto;
import com.mealmap.notificationservice.core.enums.sort.NotificationSortField;
import com.mealmap.notificationservice.core.mapper.NotificationMapper;
import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.repository.NotificationRepository;
import com.mealmap.notificationservice.service.NotificationPredicateService;
import com.mealmap.notificationservice.service.NotificationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationPredicateService predicateService;

    private final PageMapper pageMapper;

    private final NotificationMapper notificationMapper;

    private final NotificationRepository notificationRepository;

    @Override
    public PageDto<NotificationDto> getPageOfNotifications(
            Integer offset, Integer limit, NotificationSortField sortBy, Sort.Direction sortOrder,
            NotificationFilter filter, String search) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Predicate predicate = predicateService.buildPredicateForNotifications(filter, search);

        Page<Notification> orders = predicate == null
                ? notificationRepository.findAll(request)
                : notificationRepository.findAll(predicate, request);

        return pageMapper.pageToPageDto(
                notificationMapper.docPageToDtoPage(orders));
    }
}
