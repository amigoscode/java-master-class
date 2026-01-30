package com.stan.car;

import com.stan.booking.Booking;
import com.stan.booking.BookingDao;

import java.util.Iterator;
import java.util.List;

public class CarService {
    private CarDao carDao;
    private BookingDao bookingDao;

    public CarService(CarDao carDao, BookingDao bookingDao) {
        this.carDao = carDao;
        this.bookingDao = bookingDao;
    }

    public List<Car> getCars() {
        return this.carDao.getCars();
    }

    public List<Car> getAvailableCars(boolean isElectric) {
        List<Booking> bookings = bookingDao.getBookings();
        List<Car> cars;
        if (isElectric) {
            cars = getElectricCars();
        } else {
            cars = getCars();
        }
        List<Car> availableCars = getCars(isElectric, cars, bookings);

        return availableCars;
    }

    private static List<Car> getCars(boolean isElectric, List<Car> cars, List<Booking> bookings) {
        List<Car> availableCars = cars;

        Iterator<Car> iter = availableCars.iterator();
        while (iter.hasNext()) {
            Car car = iter.next();

            boolean isBooked = false;
            for (Booking booking : bookings) {
                isBooked = booking.getCar().getRegNumber().equals(car.getRegNumber());
                if (isBooked) {
                    break;
                }
            }

            if (isBooked && (!isElectric || car.isElectric())) {
                iter.remove();
            }
        }

        return availableCars;
    }

    public List<Car> getElectricCars() {
        return this.carDao.getElectricCars();
    }
}
