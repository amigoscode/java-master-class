package com.bennet.booking.model;

import org.springframework.stereotype.Component;

@Component
public class BookingResponseMapper {

    public BookingResponseDto toDto( Booking booking ) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getUserId(),
                booking.getCarId(),
                booking.getTimeSequence()
        );
    }
}
