package com.bennet.car.model;

import jakarta.validation.constraints.NotNull;

public record CarRequestDto(
        @NotNull String brand,
        @NotNull String model,
        @NotNull CarType type
) { }
