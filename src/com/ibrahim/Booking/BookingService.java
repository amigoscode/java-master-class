package com.ibrahim.Booking;

import com.ibrahim.Car.Car;
import com.ibrahim.Car.CarDao;
import com.ibrahim.Car.CarService;
import com.ibrahim.Car.PowerType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.UUID;



public class BookingService {

    private final BookingDao bookingDao = new BookingDao();
    private final CarService carService = new CarService();




    public String bookCar(UUID carId, UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        Booking[] bookings = bookingDao.getBookings();

        for (Booking booking : bookings) {
            if (booking.getCarId().equals(carId)) {
                if (booking.getStatus().equals(Status.ACTIVE)) {
                    return ("Car Already Booked");
                }
            }
        }

        Car car = carService.getCarById(carId);
        if (car == null) {
            return "Car not found";
        }

        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal carPrice = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(numberOfDays));
        Booking newBooking = new Booking(UUID.randomUUID(), carId, userId, startDate, endDate, carPrice);

        bookingDao.saveBooking(newBooking);
        carService.updateCarBooking(carId, newBooking.getBookingId());


        return newBooking.toString();
    }


    //all bookings
    public String getActiveBookings() {
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

        return Arrays.toString(activeBookings);


    }


    public String getUserBookedCars(UUID userId) {

        int userBookedCarsAmount = 0;

        for (Booking booking : bookingDao.getBookings()) {


            if (booking.getUserId().equals(userId) && booking.getStatus().equals(Status.ACTIVE)) {
                userBookedCarsAmount++;
            }
        }


        Car[] userBookedCars = new Car[userBookedCarsAmount];
        Booking[] bookings = bookingDao.getBookings();
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


        return Arrays.toString(userBookedCars);


    }

    public String getAvailableCars() {
        int availableCarAmount = 0;

        for (Booking booking : bookingDao.getBookings()) {
            if (booking.getStatus().equals(Status.CANCELLED) || booking.getStatus().equals(Status.Completed)) {
                availableCarAmount += 1;
            }
        }

        for (Car car : carService.getAllCars()) {
            if (car.getBookingId() == null) {
                availableCarAmount += 1;
            }
        }


        Car[] availableCars = new Car[availableCarAmount];
        Booking[] bookings = bookingDao.getBookings();

        int carIdx = 0;
        for (Booking booking : bookings) {

            if (booking.getStatus().equals(Status.CANCELLED) || booking.getStatus().equals(Status.Completed)) {
                Car car = carService.getCarById(booking.getCarId());
                availableCars[carIdx] = car;
                carIdx++;

            }

        }
        for (Car car : carService.getAllCars()) {
            if (car.getBookingId() == null) {
                availableCars[carIdx] = car;
                carIdx += 1;
            }
        }


        return Arrays.toString(availableCars);

    }


    public String getAvailableElectricCars() {
        int availableElectricCarAmount = 0;

        for (Booking booking : bookingDao.getBookings()) {


            if (booking.getStatus().equals(Status.CANCELLED) || booking.getStatus().equals(Status.Completed)) {
                Car car = carService.getCarById(booking.getCarId());
                if (car == null) {
                    return "Car not found";
                }
                if (car.getPowerType().equals(PowerType.ELECTRIC)) {
                    availableElectricCarAmount += 1;
                }

            }
        }
        for (Car car : carService.getAllCars()) {
            if (car.getBookingId() == null && car.getPowerType().equals(PowerType.ELECTRIC)) {
                availableElectricCarAmount += 1;
            }
        }

        Car[] availableElectricCars = new Car[availableElectricCarAmount];
        Booking[] bookings = bookingDao.getBookings();

        int carIdx = 0;
        for (Booking booking : bookings) {

            if (booking.getStatus().equals(Status.CANCELLED) || booking.getStatus().equals(Status.Completed)) {
                Car car = carService.getCarById(booking.getCarId());
                assert car != null;
                if (car.getPowerType().equals(PowerType.ELECTRIC)) {
                    availableElectricCars[carIdx] = car;
                    carIdx += 1;
                }

            }

        }

        for (Car car : carService.getAllCars()) {
            if (car.getBookingId() == null && car.getPowerType().equals(PowerType.ELECTRIC)) {
                availableElectricCars[carIdx] = car;
                carIdx += 1;
            }
        }

        return Arrays.toString(availableElectricCars);

    }

}
