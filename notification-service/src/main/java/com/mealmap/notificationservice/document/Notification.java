package com.mealmap.notificationservice.document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mealmap.notificationservice.core.serialization.ChannelDeserializer;
import com.mealmap.notificationservice.core.serialization.ChannelSerializer;
import com.mealmap.notificationservice.core.serialization.NotificationStatusDeserializer;
import com.mealmap.notificationservice.core.serialization.NotificationStatusSerializer;
import com.mealmap.notificationservice.document.enums.Channel;
import com.mealmap.notificationservice.document.enums.NotificationStatus;
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

    @JsonSerialize(using = ChannelSerializer.class)
    @JsonDeserialize(using = ChannelDeserializer.class)
    private Channel channel;

    @JsonSerialize(using = NotificationStatusSerializer.class)
    @JsonDeserialize(using = NotificationStatusDeserializer.class)
    private NotificationStatus status;

    private String subject;

    private String message;

    private LocalDateTime sentAt;
}
