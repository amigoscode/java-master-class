package com.bennet.user.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserRequestMapper implements Function<UserRequestDto, User> {

    private final PasswordEncoder passwordEncoder;
    @Override
    public User apply(UserRequestDto userRequestDto) {
        return new User(
                userRequestDto.name(),
                passwordEncoder.encode( userRequestDto.password() ),
                userRequestDto.email() );
    }
}
