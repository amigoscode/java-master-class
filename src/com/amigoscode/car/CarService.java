package com.amigoscode.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCar(String regNumber) {
        for (Car car : getAllCars()) {
            if (regNumber.equals(car.getRegNumber())) {
                return car;
            }
        }
        throw new IllegalStateException(String.format("Car with reg %s not found", regNumber));
    }

    public List<Car> getAllElectricCars() {
        List<Car> cars = getAllCars();

        if (cars.size() == 0) {
            return Collections.emptyList();
        }

        List<Car> electricCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.isElectric()) {
                electricCars.add(car);
            }
        }



        return electricCars;
    }
}
