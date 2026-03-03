package com.bennet.car.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CarResponseDto(
        @NotNull UUID id,
        @NotNull String brand,
        @NotNull String model,
        @NotNull CarType type
) { }
