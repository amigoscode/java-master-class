package com.bennet.user.model;

import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull String name,
        @NotNull String email,
        @NotNull String password
        ) {
}
