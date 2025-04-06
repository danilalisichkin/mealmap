package com.mealmap.notificationservice.doc.predicate;

import com.mealmap.notificationservice.doc.QNotification;
import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationQuerydslPredicate {
    public static BooleanExpression hasUserId(String userId) {
        return userId != null
                ? QNotification.notification.userId.eq(userId)
                : null;
    }

    public static BooleanExpression hasChannel(Channel channel) {
        return channel != null
                ? QNotification.notification.channel.eq(channel)
                : null;
    }

    public static BooleanExpression hasStatus(NotificationStatus status) {
        return status != null
                ? QNotification.notification.status.eq(status)
                : null;
    }

    public static BooleanExpression hasSubjectOrMessageLike(String search) {
        if (StringUtils.isBlank(search)) {
            return Expressions.FALSE;
        }

        return QNotification.notification.message.containsIgnoreCase(search)
                .or(QNotification.notification.subject.containsIgnoreCase(search));
    }

    public static BooleanExpression isSentAtBetween(LocalDateTime start, LocalDateTime end) {
        return createRangePredicate(start, end, QNotification.notification.sentAt);
    }

    private static BooleanExpression createRangePredicate(
            LocalDateTime start, LocalDateTime end, DateTimePath<LocalDateTime> field) {

        if (start == null && end == null) {
            return null;
        }
        BooleanExpression predicate = null;
        if (start != null) {
            predicate = field.goe(start);
        }
        if (end != null) {
            predicate = predicate == null ? field.loe(end) : predicate.and(field.loe(end));
        }
        return predicate;
    }
}
