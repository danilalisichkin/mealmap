package com.mealmap.notificationservice.service.impl;

import com.mealmap.notificationservice.core.dto.filter.NotificationFilter;
import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;
import com.mealmap.notificationservice.service.NotificationPredicateService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.mealmap.notificationservice.doc.predicate.NotificationQuerydslPredicate.hasChannel;
import static com.mealmap.notificationservice.doc.predicate.NotificationQuerydslPredicate.hasStatus;
import static com.mealmap.notificationservice.doc.predicate.NotificationQuerydslPredicate.hasSubjectOrMessageLike;
import static com.mealmap.notificationservice.doc.predicate.NotificationQuerydslPredicate.hasUserId;
import static com.mealmap.notificationservice.doc.predicate.NotificationQuerydslPredicate.isSentAtBetween;

@Service
public class NotificationPredicateServiceImpl implements NotificationPredicateService {
    @Override
    public Predicate buildPredicateForNotifications(NotificationFilter filter, String search) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        addFilterByUserId(predicateBuilder, filter.getUserId());
        addFilterByChannel(predicateBuilder, filter.getChannel());
        addFilterByStatus(predicateBuilder, filter.getStatus());
        addFilterBySentAt(predicateBuilder, filter.getSentAtStart(), filter.getSentAtEnd());
        addFilterBySubjectOrMessage(predicateBuilder, search);

        return predicateBuilder.getValue();
    }

    private void addFilterByUserId(BooleanBuilder predicateBuilder, String userId) {
        if (userId != null) {
            predicateBuilder.and(hasUserId(userId));
        }
    }

    private void addFilterByChannel(BooleanBuilder predicateBuilder, Channel channel) {
        if (channel != null) {
            predicateBuilder.and(hasChannel(channel));
        }
    }

    private void addFilterByStatus(BooleanBuilder predicateBuilder, NotificationStatus status) {
        if (status != null) {
            predicateBuilder.and(hasStatus(status));
        }
    }

    private void addFilterBySubjectOrMessage(BooleanBuilder predicateBuilder, String search) {
        if (search != null && !search.isBlank()) {
            predicateBuilder.and(hasSubjectOrMessageLike(search));
        }
    }

    private void addFilterBySentAt(BooleanBuilder predicateBuilder, LocalDateTime start, LocalDateTime end) {
        if (start != null || end != null) {
            predicateBuilder.and(isSentAtBetween(start, end));
        }
    }
}
