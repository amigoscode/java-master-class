package com.bennet.booking.repository;

import com.bennet.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUserId(UUID userId);
    List<Booking> findByCarId(UUID carId);
    Optional<Booking> findByIdempotencyKey( String idempotencyKey );
}
