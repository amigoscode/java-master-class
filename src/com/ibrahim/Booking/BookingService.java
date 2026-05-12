package com.ibrahim.Booking;

import com.ibrahim.Car.Car;
import com.ibrahim.Car.CarService;
import com.ibrahim.Car.PowerType;
import com.ibrahim.User.User;
import com.ibrahim.User.UserService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class BookingService {

    private final BookingDao bookingDao = new BookingDao();
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();

    public UUID bookCar(UUID carId, UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        Booking[] bookings = bookingDao.getBookings();
        for (Booking booking : bookings) {
            if (booking.getCarId().equals(carId)) {
                if (booking.getStatus().equals(Status.ACTIVE)) {
                    throw new IllegalStateException("car already booked");
                }
            }
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalStateException("User [" + userId + "] not found");
        }
        Car car = carService.getCarById(carId);
        if (car == null) {
            throw new IllegalStateException("Car [" + carId + "] not found");
        }
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        if(numberOfDays <= 0){
            throw new IllegalStateException("Number of days is negative");
        }
        BigDecimal carPrice = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(numberOfDays));
        Booking newBooking = new Booking(UUID.randomUUID(), carId, userId, startDate, endDate, carPrice);
        bookingDao.saveBooking(newBooking);
        return newBooking.getBookingId();
    }

    public Booking[] getActiveBookings() {
        int activeBookingAmount = 0;
        for (Booking booking : bookingDao.getBookings()) {
            if (booking.getStatus().equals(Status.ACTIVE)) {
                activeBookingAmount += 1;
            }
        }
        Booking[] activeBookings = new Booking[activeBookingAmount];
        Booking[] bookings = bookingDao.getBookings();
        int bookingIdx = 0;
        for (Booking booking : bookings) {
            if (booking.getStatus().equals(Status.ACTIVE)) {
                activeBookings[bookingIdx] = booking;
                bookingIdx++;
            }
        }
        return activeBookings;
    }

    public Car[] getUserBookedCars(UUID userId) {
        Booking[] bookings = bookingDao.getBookings();
        int userBookedCarsAmount = 0;
        for (Booking booking : bookings) {
            if (booking.getUserId().equals(userId) && booking.getStatus().equals(Status.ACTIVE)) {
                userBookedCarsAmount++;
            }
        }
        Car[] userBookedCars = new Car[userBookedCarsAmount];
        int userIdx = 0;
        for (Booking booking : bookings) {
            if (booking.getUserId().equals(userId)) {
                if (booking.getStatus().equals(Status.ACTIVE)) {
                    Car car = carService.getCarById(booking.getCarId());
                    userBookedCars[userIdx] = car;
                    userIdx += 1;
                }
            }
        }
        return userBookedCars;
    }

    private boolean isCarAvailableForRental(UUID carId) {
        boolean isAvailable = true;
        for (Booking booking : bookingDao.getBookings()) {
            if (booking.getCarId().equals(carId) && booking.getStatus() == Status.ACTIVE) {
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }

    public Car[] getAvailableCars() {
        int availableCarAmount = 0;
        Car[] allCars = carService.getAllCars();
        for (Car car : allCars) {
            if (isCarAvailableForRental(car.getCarId())) {
                availableCarAmount += 1;
            }
        }
        Car[] availableCars = new Car[availableCarAmount];
        int carIdx = 0;
        for (Car car : allCars) {
            if (isCarAvailableForRental(car.getCarId())) {
                availableCars[carIdx] = car;
                carIdx += 1;
            }
        }
        return availableCars;
    }

    public Car[] getAvailableElectricCars() {
        int availableElectricCarAmount = 0;
        Car[] allCars = carService.getAllCars();
        for (Car car : allCars) {
            if (car.getPowerType().equals(PowerType.ELECTRIC) && isCarAvailableForRental(car.getCarId())) {
                availableElectricCarAmount += 1;
            }
        }
        Car[] availableElectricCars = new Car[availableElectricCarAmount];
        int carIdx = 0;
        for (Car car : allCars) {
            if (car.getPowerType().equals(PowerType.ELECTRIC) && isCarAvailableForRental(car.getCarId())) {
                availableElectricCars[carIdx] = car;
                carIdx += 1;
            }
        }
        return availableElectricCars;
    }

    public boolean cancelBooking(UUID bookingId) {
        for (Booking booking : bookingDao.getBookings()) {
            if (booking == null) {
                continue;
            }
            if (booking.getBookingId().equals(bookingId)) {
                booking.setStatus(Status.CANCELLED);
                booking.setEndDate(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }
}
