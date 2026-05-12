package com.ibrahim.Booking;

import java.util.Arrays;

public class BookingDao {

    private Booking[] bookings;

    {
        bookings = new Booking[100];
    }

    public Booking[] getBookings() {
        int bookingAmount = 0;

        for (Booking booking : bookings) {
            if (booking != null) {
                bookingAmount++;
            }
        }

        Booking[] savedBookings = new Booking[bookingAmount];
        int bookingIdx = 0;

        for (Booking booking : bookings) {
            if (booking != null) {
                savedBookings[bookingIdx] = booking;
                bookingIdx++;
            }
        }

        return savedBookings;
    }

    public boolean saveBooking(Booking newBooking) {

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                bookings[i] = newBooking;
                return true;
            }
        }

        try {
            Booking[] newBookings = Arrays.copyOf(bookings, bookings.length + 1);
            newBookings[bookings.length] = newBooking;
            bookings = newBookings;
        } catch (Exception e) {
            System.err.println("Failed saving new booking");
            return false;
        }
        return true;

    }

}
