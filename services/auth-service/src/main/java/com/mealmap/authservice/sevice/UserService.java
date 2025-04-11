package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.dto.UserStatus;
import com.mealmap.authservice.core.enums.Role;
import org.keycloak.representations.idm.UserRepresentation;

public interface UserService {
    UserRepresentation createUser(UserRegisterDto userRegisterDto, Role role, UserStatus status);
}
