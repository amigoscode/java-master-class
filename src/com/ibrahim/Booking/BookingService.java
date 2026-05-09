package com.ibrahim.Booking;






import com.ibrahim.Car.CarService;
import com.ibrahim.User.UserService;

import java.util.*;

import static com.ibrahim.Booking.BookingDao.getBookings;
import static com.ibrahim.Booking.BookingDao.setBookings;
import static com.ibrahim.Utils.IDHelpers.promptForUUID;


public class BookingService
{


    //    user booking car
    public static void addNewBooking(){

        Scanner scanner = new Scanner(System.in);

        UUID userId = promptForUUID(scanner, "What is the UserId");
        if (UserService.getUserById(userId) == null) {
            System.out.println("User doesn't exist");
            return;
        }


        UUID carId = promptForUUID(scanner, "What is the CarId");
        if (CarService.getCarById(carId) == null) {
            System.out.println("Car doesn't exist");
            return;
        }



        Booking newBooking = new Booking(UUID.randomUUID(),carId, userId);

        Booking[] oldBookings = getBookings();

        Booking[] newBookings = Arrays.copyOf(oldBookings, oldBookings.length + 1);

        newBookings[newBookings.length - 1] = newBooking;

        setBookings(newBookings);

        System.out.println("Added new booking:"+ newBooking.toString());



    };


    //all bookings
    public static void getALLBookings(){

        for(Booking booking : getBookings()){
            System.out.println(booking.toString());

        }


    }


    public static void getAllUserBookedCars(){
        Scanner scanner = new Scanner(System.in);
        UUID userId = promptForUUID(scanner, "What is the UserId");
        if (UserService.getUserById(userId) == null) {
            System.out.println("User doesn't exist");
            return;
        }

        List<Booking> userBookings = new ArrayList<>();



        Booking[] bookings = getBookings();

        //probably not best way
        if (bookings == null || bookings.length == 0) {
            System.out.println("No bookings found");
            return;
        }

        for(Booking booking: getBookings()){
            if (booking.getUserId().equals(UUID.fromString(String.valueOf(userId)))){
                if(booking.getStatus().equals(Status.ACTIVE)){
                    userBookings.add(booking);
                }

            }
        }

        //Handle no active user booking
        if(userBookings.isEmpty()){
            System.out.println("User has no active bookings");
        }


        //return user booking in format
        for(Booking booking : userBookings){

            System.out.println(booking.toString());



        }



    }


}
