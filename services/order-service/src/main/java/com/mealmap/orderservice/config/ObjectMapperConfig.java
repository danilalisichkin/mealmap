package com.mealmap.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mealmap.orderservice.core.serialization.ObjectIdDeserializer;
import com.mealmap.orderservice.core.serialization.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(ObjectId.class, new ObjectIdSerializer());
        module.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
        mapper.registerModule(module);

        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
