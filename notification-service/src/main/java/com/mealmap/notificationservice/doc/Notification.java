package com.mealmap.notificationservice.doc;

import com.mealmap.notificationservice.doc.enums.Channel;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("notifications")
public class Notification {
    @Id
    private ObjectId id;

    @Indexed
    private String userId;

    private Channel channel;

    private NotificationStatus status;

    private String subject;

    private String message;

    private LocalDateTime sentAt;
}
