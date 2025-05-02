package com.mealmap.notificationservice.core.dto.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
public class UserContactsDto {
    private ObjectId id;

    private String userId;

    private String email;

    private String phoneNumber;

    private Long tgChatId;
}
