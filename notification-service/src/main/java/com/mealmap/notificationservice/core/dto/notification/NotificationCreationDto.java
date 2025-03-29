package com.mealmap.notificationservice.core.dto.notification;

import com.mealmap.notificationservice.document.enums.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreationDto {
    private Channel channel;

    private String subject;

    private String message;
}
