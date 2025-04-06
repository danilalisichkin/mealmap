package com.mealmap.notificationservice.core.dto.notification;

import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class NotificationDto {
    private ObjectId id;

    private String userId;

    private Channel channel;

    private NotificationStatus status;

    private String subject;

    private String message;

    private LocalDateTime sentAt;
}
