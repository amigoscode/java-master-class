package com.bennet.user.model;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email
) {
}
