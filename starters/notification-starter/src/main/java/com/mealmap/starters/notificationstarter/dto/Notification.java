package com.mealmap.starters.notificationstarter.dto;

import com.mealmap.starters.notificationstarter.enums.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    private UUID userId;
    private Channel channel;
    private String subject;
    private String message;
}
