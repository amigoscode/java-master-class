package com.ibrahim.Booking;



import java.util.UUID;

public class BookingDao {

    private static  Booking[] bookings;

    static {
        bookings = new Booking[]{

        };
    }


    public static Booking[] getBookings() {
        return bookings;
    }

    public static void setBookings(Booking[] newBookings){
        bookings = newBookings;

    }

}
