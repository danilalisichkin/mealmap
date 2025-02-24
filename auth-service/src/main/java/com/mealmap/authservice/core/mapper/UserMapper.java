package com.mealmap.authservice.core.mapper;

import com.mealmap.authservice.core.dto.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_ACTIVE;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_BLOCKED;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.IS_TEMPORARY_BLOCKED;
import static com.mealmap.authservice.keycloak.constant.UserAttributes.ORGANIZATION_ID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "organizationId", source = "attributes." + ORGANIZATION_ID, qualifiedByName = "integer")
    @Mapping(target = "isActive", source = "attributes." + IS_ACTIVE, qualifiedByName = "boolean")
    @Mapping(target = "isTemporaryBlocked", source = "attributes." + IS_TEMPORARY_BLOCKED, qualifiedByName = "boolean")
    @Mapping(target = "isBlocked", source = "attributes." + IS_BLOCKED, qualifiedByName = "boolean")
    @Mapping(target = "createdAt", source = "createdTimestamp", qualifiedByName = "localDate")
    @Mapping(target = "phoneNumber", source = "username")
    @Mapping(target = "role", source = "realmRoles.first")
    UserDto keycloakRepresentationToDto(UserRepresentation representation);

    @Named("integer")
    default Integer mapOrganizationId(List<String> attribute) {
        return attribute != null && !attribute.isEmpty()
                ? Integer.parseInt(attribute.getFirst())
                : null;
    }

    @Named("localDate")
    default LocalDate mapLocalDate(Long timestamp) {
        return timestamp == null
                ? null
                : Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Named("boolean")
    default Boolean mapBoolean(List<String> attribute) {
        return attribute != null && !attribute.isEmpty()
                ? Boolean.valueOf(attribute.getFirst())
                : null;
    }
}
