package com.mealmap.userservice.core.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mealmap.userservice.entity.enums.UserRole;

import java.io.IOException;

import static com.mealmap.userservice.core.constant.Prefix.ROLE;

public class UserRoleDeserializer extends JsonDeserializer<UserRole> {
    @Override
    public UserRole deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        String value = jsonParser.getText();

        return value.startsWith(ROLE)
                ? UserRole.valueOf(value.substring(ROLE.length()))
                : null;
    }
}
