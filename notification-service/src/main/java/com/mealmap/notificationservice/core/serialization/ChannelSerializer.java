package com.mealmap.notificationservice.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mealmap.notificationservice.document.enums.Channel;

import java.io.IOException;

public class ChannelSerializer extends JsonSerializer<Channel> {
    @Override
    public void serialize(Channel channel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeNumber(channel.getId());
    }
}
