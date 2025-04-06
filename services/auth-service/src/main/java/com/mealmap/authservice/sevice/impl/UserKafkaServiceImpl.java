package com.mealmap.authservice.sevice.impl;

import com.mealmap.authservice.core.dto.UserDto;
import com.mealmap.authservice.kafka.dto.KafkaUserRoleUpdateDto;
import com.mealmap.authservice.kafka.dto.KafkaUserStatusUpdateDto;
import com.mealmap.authservice.kafka.dto.KafkaUserUpdateDto;
import com.mealmap.authservice.kafka.mapper.UserKafkaMapper;
import com.mealmap.authservice.kafka.producer.UserCreationProducer;
import com.mealmap.authservice.sevice.KeycloakResourceService;
import com.mealmap.authservice.sevice.KeycloakRoleService;
import com.mealmap.authservice.sevice.UserKafkaService;
import com.mealmap.authservice.util.UserAttributesBuilder;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserKafkaServiceImpl implements UserKafkaService {
    private final UserKafkaMapper userKafkaMapper;

    private final UserCreationProducer userCreationProducer;

    private final KeycloakRoleService kcRoleService;

    private final KeycloakResourceService kcResourceService;

    @Override
    public void createUser(UserDto dto) {
        userCreationProducer.sendMessage(
                userKafkaMapper.DtoToCreationDto(dto));
    }

    @Override
    public void updateUser(KafkaUserUpdateDto updateDto) {
        UserRepresentation oldUserRepresentation =
                kcResourceService.findUserResourceByUserId(updateDto.getId().toString())
                        .toRepresentation();

        Map<String, List<String>> attributes =
                UserAttributesBuilder.buildFromOrganizationId(updateDto.getOrganizationId());

        Map<String, List<String>> updatedAttributes = updateAttributes(oldUserRepresentation, attributes);

        kcResourceService.updateUser(
                updateDto.getId().toString(),
                updateDto.getEmail(),
                updateDto.getFirstName(),
                updateDto.getLastName(),
                updatedAttributes);
    }

    @Override
    public void updateUserRole(KafkaUserRoleUpdateDto updateDto) {
        UserResource userResource = kcResourceService.findUserResourceByUserId(updateDto.getId().toString());

        kcRoleService.unassignRoleFromUser(userResource, updateDto.getOldRole());
        kcRoleService.assignRoleToUser(userResource, updateDto.getNewRole());
    }

    @Override
    public void updateUserStatus(KafkaUserStatusUpdateDto updateDto) {
        UserRepresentation oldUserRepresentation =
                kcResourceService.findUserResourceByUserId(updateDto.getId().toString())
                        .toRepresentation();

        Map<String, List<String>> attributes =
                UserAttributesBuilder.buildFromStatus(updateDto.getStatus());

        Map<String, List<String>> updatedAttributes = updateAttributes(oldUserRepresentation, attributes);

        kcResourceService.updateUser(
                updateDto.getId().toString(),
                oldUserRepresentation.getEmail(),
                oldUserRepresentation.getFirstName(),
                oldUserRepresentation.getLastName(),
                updatedAttributes);
    }

    private Map<String, List<String>> updateAttributes(
            UserRepresentation userRepresentation, Map<String, List<String>> attributes) {

        Map<String, List<String>> currentAttributes = userRepresentation.getAttributes();

        return currentAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> attributes
                                .containsKey(entry.getKey())
                                ? attributes.get(entry.getKey())
                                : entry.getValue()));
    }
}
