package com.ibrahim.Car;

import java.util.Objects;
import java.util.UUID;

public class CarService {

    public static Car getCarById(UUID carId) {
        for (Car car : CarDao.getCars()) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }

    public static void updateCarBooking(UUID carId, UUID newBookingid){

        Objects.requireNonNull(getCarById(carId)).setBookingId(newBookingid);

    }


}
