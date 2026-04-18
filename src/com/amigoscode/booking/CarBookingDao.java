package com.amigoscode.booking;

import java.util.UUID;

public class CarBookingDao {

    private final static CarBooking[] carBookings;

    static {
        carBookings = new CarBooking[10];
    }

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

    public void book(CarBooking carBooking) {
        int nextFreeIndex = -1;

        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i] == null) {
                nextFreeIndex = i;
            }
        }

        if (nextFreeIndex > -1) {
            carBookings[nextFreeIndex] = carBooking;
            return;
        }

        // full array
        // copy all bookings to new array with bigger space
        CarBooking[] biggerCarBookings = new CarBooking[carBookings.length + 10];

        for (int i = 0; i < carBookings.length; i++) {
            biggerCarBookings[i] = carBookings[i];
        }

        // finally add new booking
        biggerCarBookings[carBookings.length] = carBooking;

    }

    public void cancelCarBooking(UUID id) {

    }

}
