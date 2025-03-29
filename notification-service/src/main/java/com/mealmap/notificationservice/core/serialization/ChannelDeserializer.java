package com.mealmap.notificationservice.core.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mealmap.notificationservice.document.enums.Channel;

import java.io.IOException;

public class ChannelDeserializer extends JsonDeserializer<Channel> {
    @Override
    public Channel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        int id = jsonParser.getIntValue();

        return Channel.fromId(id);
    }
}
