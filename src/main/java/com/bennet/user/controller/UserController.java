package com.bennet.user.controller;

import com.bennet.user.model.UserRequestDto;
import com.bennet.user.model.UserResponseDto;
import com.bennet.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserRequestDto userRequestDto,
            UriComponentsBuilder uriBuilder) {

        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        URI location = uriBuilder.path("/api/v1/users/{id}")
                .buildAndExpand(userResponseDto.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .contentType( MediaType.APPLICATION_JSON )
                .body(userResponseDto);
    }
}
