package com.mealmap.authservice.strategy;

import com.mealmap.authservice.core.mapper.UserMapper;
import com.mealmap.authservice.sevice.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UserRegistrationBaseHandler implements UserRegistrationHandler {
    protected final UserService userService;

    protected final UserMapper userMapper;
}
