package com.ibrahim.Booking;

import java.util.UUID;

public interface BookingDao {
    Booking[] getBookings();
    Booking findBookingById(UUID bookingId);
    void saveBooking(Booking newBooking);
    void deleteBooking(UUID bookingId);
}
