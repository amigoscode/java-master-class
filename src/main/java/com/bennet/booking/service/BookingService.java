package com.bennet.booking.service;

import com.bennet.booking.model.Booking;
import com.bennet.booking.model.BookingRequestDto;
import com.bennet.booking.model.BookingRequestMapper;
import com.bennet.booking.model.BookingResponseDto;
import com.bennet.booking.model.BookingResponseMapper;
import com.bennet.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingRequestMapper bookingRequestMapper;
    private final BookingResponseMapper bookingResponseMapper;

    public List<BookingResponseDto> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map( bookingResponseMapper::toDto )
                .toList();
    }

    public List<BookingResponseDto> getBookingsByUserId( UUID userId ) {
        return bookingRepository.findByUserId( userId )
                .stream()
                .map( bookingResponseMapper::toDto )
                .toList();
    }

    public boolean isCarAvailable( UUID carId ) {
        return bookingRepository.findByCarId( carId ).isEmpty();
    }

    public List<BookingResponseDto> getBookingsByCarId( UUID carId ) {
        return  bookingRepository.findByCarId( carId )
                .stream()
                .map( bookingResponseMapper::toDto ).toList();
    }

    public BookingResponseDto createBooking( BookingRequestDto bookingRequestDto, String userId ) {

        String idempotencyKey = generateIdempotencyKey( userId, bookingRequestDto );

        // idempotency: check if booking is already processed
        Optional<Booking> bookingDb = bookingRepository.findByIdempotencyKey( idempotencyKey );

        if ( bookingDb.isPresent() ) {
            log.info( "Found existing booking for car: {} and user: {}", bookingDb.get().getCarId(), bookingDb.get().getUserId() );
            return bookingResponseMapper.toDto( bookingDb.get() );
        } else {
            log.info( "Creating booking for car: {} and user: {}", bookingRequestDto.carId(), bookingRequestDto.userId() );
            Booking booking = bookingRepository.save( bookingRequestMapper.toEntity( bookingRequestDto, idempotencyKey ) );
            return bookingResponseMapper.toDto( booking );
        }
    }

    private String generateIdempotencyKey ( String userId, BookingRequestDto bookingRequestDto ) {
        String key = userId + bookingRequestDto.carId();
        return DigestUtils.md5DigestAsHex( key.getBytes() );
    }
}
