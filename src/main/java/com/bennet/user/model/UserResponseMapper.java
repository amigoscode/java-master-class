package com.bennet.user.model;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserResponseMapper implements Function<User, UserResponseDto> {
    @Override
    public UserResponseDto apply(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail() );
    }
}
