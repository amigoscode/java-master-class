package com.amigoscode.car;

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car[] getAllCars() {
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

    public Car[] getAllElectricCars() {
        int electricCarsCount = 0;

        Car[] cars = getAllCars();

        if (cars.length == 0) {
            return new Car[0];
        }

        for (Car car : cars) {
            if (car.isElectric()) {
                electricCarsCount++;
            }
        }

        if (electricCarsCount == 0) {
            return new Car[0];
        }

        Car[] electricCars = new Car[electricCarsCount];

        int index = 0;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].isElectric()) {
                electricCars[index++] = cars[i];
            }
        }

        return electricCars;
    }
}
