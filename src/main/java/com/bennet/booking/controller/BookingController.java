package com.bennet.booking.controller;

import com.bennet.booking.model.BookingRequestDto;
import com.bennet.booking.model.BookingResponseDto;
import com.bennet.booking.repository.BookingRepository;
import com.bennet.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @GetMapping
    public List<BookingResponseDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto bookingRequestDto,
            @AuthenticationPrincipal UserDetails userDetails,
            UriComponentsBuilder uriBuilder
    ) {

        URI location = uriBuilder.path("/api/v1/bookings/{id}")
                .buildAndExpand(bookingRequestDto.carId())
                .toUri();

        BookingResponseDto dto = bookingService.createBooking(bookingRequestDto, userDetails.getUsername());
        return ResponseEntity
                .created( location )
                .contentType( MediaType.APPLICATION_JSON )
                .body(dto);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponseDto> getBookingsByUserId(@PathVariable UUID userId) {
        return bookingService.getBookingsByUserId(userId);
    }
}
