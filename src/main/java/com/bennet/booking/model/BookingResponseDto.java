package com.bennet.booking.model;

import com.bennet.booking.value_objects.TimeSequence;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookingResponseDto(
        @NotNull UUID id,
        @NotNull UUID userId,
        @NotNull UUID carId,
        @NotNull TimeSequence timeSequence
) { }
