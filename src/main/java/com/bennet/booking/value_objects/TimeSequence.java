package com.bennet.booking.value_objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Embeddable
public record TimeSequence(
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public TimeSequence {
        Objects.requireNonNull(startDate, "Start date must not be null");
        Objects.requireNonNull(endDate, "End date must not be null");
        if (!startDate.isBefore(endDate)) {
            log.error("Invalid TimeSequence: startDate {} is not before endDate {}", startDate, endDate);
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    @JsonIgnore
    public boolean isValid() {
        return startDate.isBefore(endDate);
    }
}
