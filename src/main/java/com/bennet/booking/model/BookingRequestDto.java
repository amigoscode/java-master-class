package com.bennet.booking.model;

import com.bennet.booking.value_objects.TimeSequence;
import com.bennet.car.model.CarResponseDto;
import com.bennet.user.model.UserResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookingRequestDto(
        @NotNull UUID userId,
        @NotNull UUID carId,
        @Valid @NotNull TimeSequence timeSequence
) {
}
