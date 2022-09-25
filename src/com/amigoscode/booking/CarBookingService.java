package com.amigoscode.booking;

import com.amigoscode.car.Car;
import com.amigoscode.car.CarService;
import com.amigoscode.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingDao carBookingDao;
    private final CarService carService;

    public CarBookingService(CarBookingDao carBookingDao, CarService carService) {
        this.carBookingDao = carBookingDao;
        this.carService = carService;
    }

    public UUID bookCar(User user, String regNumber) {
        List<Car> availableCars = getAvailableCars();

        if (availableCars.isEmpty()) {
            throw new IllegalStateException("No car available for renting");
        }

        for (Car availableCar : availableCars) {
            // let's make sure the car user wants still available
            if (availableCar.getRegNumber().equals(regNumber)) {
                Car car = carService.getCar(regNumber);
                UUID bookingId = UUID.randomUUID();
                carBookingDao.book(
                        new CarBooking(bookingId, user, car, LocalDateTime.now())
                );
                // at this point we are done therefore we can exit this method
                return bookingId;
            }
        }
        throw new IllegalStateException("Already booked. car with regNumber " + regNumber);
    }

    public List<Car> getUserBookedCars(UUID userId) {
        List<CarBooking> carBookings = carBookingDao.getCarBookings();
        List<Car> userCars = new ArrayList<>();

        for (CarBooking carBooking : carBookings) {
            if (carBooking != null && carBooking.getUser().getId().equals(userId)) {
                userCars.add(carBooking.getCar());
            }
        }
        return userCars;
    }


    public List<Car> getAvailableCars() {
        return getCars(carService.getAllCars());
    }

    public List<Car> getAvailableElectricCars() {
        return getCars(carService.getAllElectricCars());
    }

    private List<Car> getCars(List<Car> cars) {

        // no cars in the system yet
        if (cars.isEmpty()) {
            return Collections.emptyList();
        }

        List<CarBooking> carBookings = carBookingDao.getCarBookings();

        // no bookings yet therefore all cars are available
        if (carBookings.isEmpty()) {
            return cars;
        }


        List<Car> availableCars = new ArrayList<>();

        // populate available cars
        for (Car car : cars) {
            // lets check if car part of any booking.
            // if not then its available but this time we add it to available cars
            boolean booked = false;
            for (CarBooking carBooking : carBookings) {
                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars.add(car);
            }
        }

        return availableCars;
    }

    public List<CarBooking> getBookings() {
        return carBookingDao.getCarBookings();

    }
}
