package com.mealmap.notificationservice.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mealmap.notificationservice.doc.enums.NotificationStatus;

import java.io.IOException;

public class NotificationStatusSerializer extends JsonSerializer<NotificationStatus> {
    @Override
    public void serialize(NotificationStatus notificationStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeNumber(notificationStatus.getId());
    }
}
