package com.stan.booking;

import com.stan.car.Car;
import com.stan.car.CarService;
import com.stan.user.User;
import com.stan.user.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    private BookingDao bookingDao;
    private UserDao userDao;
    private CarService carService;

    public BookingService(BookingDao bookingDao, UserDao userDao, CarService carService) {
        this.bookingDao = bookingDao;
        this.userDao = userDao;
        this.carService = carService;
    }

    public List<Booking> getBookings() {
        return this.bookingDao.getBookings();
    }

    public List<Booking> getBookingsByUserId(UUID userId) {
        List<Booking> bookings = getBookings();
        List<Booking> userBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking == null) {
                continue;
            }
            if (booking.getUser().getUserId().equals(userId)) {
               userBookings.add(booking);
            }
        }

        return userBookings;
    }

    public int getCurrentBookingNumber() {
        return this.bookingDao.getCurBookingIdx();
    }


    public List<Car> getCarsByUserId(UUID userId) {
        if (bookingDao.getCurBookingIdx() == 0) {
            return new ArrayList<>();
        }

        List<Booking> userBookings = getBookingsByUserId(userId);
        List<Car> userCars = new ArrayList<>();
        for (Booking booking : userBookings) {
            userCars.add(booking.getCar());
        }
        return userCars;
    }

    public Booking createBooking(String carRegNumber, UUID userId) {
        // Do I need to robustly handle invalid car reg number and/or user ids for now?
        List<Car> cars = carService.getAvailableCars(false);
        Car foundCar  = cars.stream().filter(car -> car.getRegNumber().equals(carRegNumber)).findFirst().orElse(null);
        if (foundCar == null) {
            System.out.println("❌ Unable to book car that doesn't exist or is unavailable");
            return null;
        }

        List<User> users = userDao.getUsers();
        User foundUser = users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
        if (foundUser == null) {
            System.out.println("❌ Unable to book car for user that doesn't exist");
            return null;
        }

        Booking booking = bookingDao.createBooking(foundCar, foundUser);
        System.out.println("🎉 Successfully booked car with reg number " + carRegNumber + " for user " + foundUser);
        System.out.println(String.format("Booking ref: %s", booking.getBookingId().toString()));
        return booking;
    }
}
