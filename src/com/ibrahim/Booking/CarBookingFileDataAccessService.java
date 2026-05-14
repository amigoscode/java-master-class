package com.ibrahim.Booking;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;

public class CarBookingFileDataAccessService implements BookingDao {

    private final String FILE_PATH;

    public CarBookingFileDataAccessService(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    @Override
    public Booking[] getBookings() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new Booking[0];
        }
        try (
                FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            Booking[] currBookings = (Booking[]) objectInputStream.readObject();
            int bookingAmount = 0;
            for (Booking booking : currBookings) {
                if (booking != null) {
                    bookingAmount++;
                }
            }
            Booking[] savedBookings = new Booking[bookingAmount];
            int bookingIdx = 0;
            for (Booking booking : currBookings) {
                if (booking != null) {
                    savedBookings[bookingIdx] = booking;
                    bookingIdx++;
                }
            }
            return savedBookings;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed reading bookings from file");
            return new Booking[0];
        }
    }

    @Override
    public void saveBooking(Booking newBooking) {
        Booking[] bookings = getBookings();
        Booking[] newBookings = Arrays.copyOf(bookings, bookings.length + 1);
        newBookings[bookings.length] = newBooking;
        writeBookingsToFile(newBookings);
    }

    @Override
    public Booking findBookingById(UUID bookingId) {
        Booking[] bookings = getBookings();
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        throw new IllegalStateException("Booking doesn't exist");
    }

    public int findBookingIndexById(UUID bookingId) {
        Booking[] bookings = getBookings();
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getBookingId().equals(bookingId)) {
                return i;
            }
        }
        throw new IllegalStateException("Booking doesn't exist");
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        Booking[] bookings = getBookings();
        int amountToKeep = 0;
        for (Booking booking : bookings) {
            if (!booking.getBookingId().equals(bookingId)) {
                amountToKeep++;
            }
        }
        Booking[] updatedBookings = new Booking[amountToKeep];
        int index = 0;
        for (Booking booking : bookings) {
            if (!booking.getBookingId().equals(bookingId)) {
                updatedBookings[index] = booking;
                index++;
            }
        }
        writeBookingsToFile(updatedBookings);
    }

    private boolean writeBookingsToFile(Booking[] bookings) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(bookings);
            return true;
        } catch (IOException e) {
            System.err.println("Failed writing bookings to file");
            return false;
        }
    }
}