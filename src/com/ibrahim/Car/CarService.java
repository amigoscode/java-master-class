package com.ibrahim.Car;

import java.util.UUID;

public class CarService {

    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car[] getAllCars() {
        return carDao.getCars();
    }

    public Car findCarById(UUID carId) {
        Car car =  carDao.findCarById(carId);
        if (car == null) {
            throw new IllegalArgumentException("Car doesn't exist");
        }
        return car;
    }
}
