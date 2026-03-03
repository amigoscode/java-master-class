package com.bennet.booking.model;

import org.springframework.stereotype.Component;

@Component
public class BookingRequestMapper {
    public Booking toEntity ( BookingRequestDto dto, String idempotencyKey ) {
        return new Booking(
                dto.userId(),
                dto.carId(),
                dto.timeSequence(),
                idempotencyKey
        );
    }

}
