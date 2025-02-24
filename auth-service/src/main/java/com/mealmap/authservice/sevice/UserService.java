package com.mealmap.authservice.sevice;

import com.mealmap.authservice.core.dto.UserRegisterDto;
import com.mealmap.authservice.core.dto.UserStatus;
import com.mealmap.authservice.core.enums.UserRole;
import org.keycloak.representations.idm.UserRepresentation;

public interface UserService {
    UserRepresentation createUser(UserRegisterDto userRegisterDto, UserRole role, UserStatus status);
}
