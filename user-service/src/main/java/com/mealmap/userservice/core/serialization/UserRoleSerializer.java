package com.mealmap.userservice.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mealmap.userservice.entity.enums.UserRole;

import java.io.IOException;

import static com.mealmap.userservice.core.constant.Prefix.ROLE;

public class UserRoleSerializer extends JsonSerializer<UserRole> {
    @Override
    public void serialize(UserRole userRole, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        if (userRole != null) {
            jsonGenerator.writeString(ROLE + userRole.name());
        } else {
            jsonGenerator.writeNull();
        }
    }
}
