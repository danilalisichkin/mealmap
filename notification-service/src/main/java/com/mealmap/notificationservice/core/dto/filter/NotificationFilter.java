package com.mealmap.notificationservice.core.dto.filter;

import com.mealmap.notificationservice.document.enums.Channel;
import com.mealmap.notificationservice.document.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class NotificationFilter {
    private Channel channel;

    private NotificationStatus status;

    private LocalDateTime sentAt;

    private LocalDateTime sentAtStart;

    private LocalDateTime sentAtEnd;
}
