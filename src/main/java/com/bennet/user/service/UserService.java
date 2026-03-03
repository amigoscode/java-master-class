package com.bennet.user.service;

import com.bennet.user.model.User;
import com.bennet.user.model.UserRequestDto;
import com.bennet.user.model.UserRequestMapper;
import com.bennet.user.model.UserResponseDto;
import com.bennet.user.model.UserResponseMapper;
import com.bennet.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public List<UserResponseDto> getAllUsers() {
        log.info("Fetching all users from repository");
        return userRepository.findAll().stream().map( userResponseMapper ).toList();
    }

    public UserResponseDto getUserById ( UUID userId ) {
        return userRepository.findAll().stream()
                .filter( user -> user.getId().equals(userId) )
                .map( userResponseMapper )
                .findFirst()
                .orElseThrow( () -> new RuntimeException("User not found with id: " + userId) );
    }

    public UserResponseDto createUser ( @Valid UserRequestDto userRequestDto ) {
        User persistetUser = userRepository.save( userRequestMapper.apply( userRequestDto ) );
        return userResponseMapper.apply( persistetUser );
    }
}
