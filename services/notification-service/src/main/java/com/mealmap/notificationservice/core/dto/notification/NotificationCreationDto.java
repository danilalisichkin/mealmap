package com.mealmap.notificationservice.core.dto.notification;

import com.mealmap.notificationservice.doc.enums.Channel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для создания уведомления")
public class NotificationCreationDto {
    @NotNull
    private Channel channel;

    @NotBlank
    @Size(max = 4000)
    private String subject;

    @NotBlank
    @Size(max = 4000)
    private String message;
}
