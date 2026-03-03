package com.bennet.booking.value_objects;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TimeSequenceTest {

    @Test
    void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.minusDays(1);

        assertThatThrownBy(() -> new TimeSequence(start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start date must be before end date");
    }

    @Test
    void shouldThrowExceptionWhenDatesAreNull() {
        assertThatThrownBy(() -> new TimeSequence(null, null))
                .isInstanceOf(NullPointerException.class);
    }
}
