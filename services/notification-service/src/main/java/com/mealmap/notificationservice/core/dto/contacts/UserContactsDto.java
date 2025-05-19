package com.mealmap.notificationservice.core.dto.contacts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Информация о контактных данных пользователя")
public class UserContactsDto {
    private ObjectId id;

    private String userId;

    private String email;

    private String phoneNumber;

    private Long tgChatId;
}
