package com.ibrahim.Booking;

import java.util.Arrays;
import java.util.UUID;

public class CarBookingArrayDataAccessService implements BookingDao {

    private Booking[] bookings;

    {
        bookings = new Booking[100];
    }

    @Override
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

    @Override
    public void saveBooking(Booking newBooking) {
        boolean canSaveBooking = false;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                bookings[i] = newBooking;
                canSaveBooking = true;
                break;
            }
        }
        if(!canSaveBooking){
            Booking[] newBookings = Arrays.copyOf(bookings, bookings.length * 2);
            newBookings[bookings.length] = newBooking;
            bookings = newBookings;
        }
    }

    public Booking findBookingById(UUID bookingId){
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        throw new IllegalStateException("Booking doesn't exist");
    }

    public int findBookingIndexById(UUID bookingId){
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getBookingId().equals(bookingId)) {
                return i;
            }
        }
        throw new IllegalStateException("Booking doesn't exist");
    }

    public void deleteBooking(UUID bookingId) {
        int toBeDeletedBookingIdx = findBookingIndexById(bookingId);
        bookings[toBeDeletedBookingIdx] = null;
    }
}
