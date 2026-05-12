package com.ibrahim.Car;

import java.util.UUID;

public class CarService {

    private final CarDao carDao = new CarDao();

    public Car[] getAllCars() {
        return carDao.getCars();
    }

    public Car getCarById(UUID carId) {
        for (Car car : carDao.getCars()) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
}
