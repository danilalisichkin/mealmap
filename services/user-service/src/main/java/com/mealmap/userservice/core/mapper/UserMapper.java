package com.mealmap.userservice.core.mapper;

import com.mealmap.userservice.core.dto.user.UserDto;
import com.mealmap.userservice.core.dto.user.UserUpdatingDto;
import com.mealmap.userservice.entity.User;
import com.mealmap.userservice.entity.UserRole;
import com.mealmap.userservice.entity.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto entityToDto(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(@MappingTarget User entity, UserUpdatingDto dto);

    List<UserDto> entityListToDtoList(List<User> entityList);

    default Page<UserDto> entityPageToDtoPage(Page<User> entityPage) {
        return new PageImpl<>(
                entityListToDtoList(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }

    default List<Role> userRoleListToRoleList(List<UserRole> userRoleList) {
        return userRoleList.stream().map(UserRole::getRole).toList();
    }
}
