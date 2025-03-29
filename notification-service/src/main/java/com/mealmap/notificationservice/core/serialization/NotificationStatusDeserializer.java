package com.mealmap.notificationservice.core.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mealmap.notificationservice.document.enums.NotificationStatus;

import java.io.IOException;

public class NotificationStatusDeserializer extends JsonDeserializer<NotificationStatus> {
    @Override
    public NotificationStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        int id = jsonParser.getIntValue();

        return NotificationStatus.fromId(id);
    }
}
