package com.stan.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.stan.car.Brand.*;

public class CarDao {

    private static final List<Car> cars;

    static {
        cars = new ArrayList<>(
                Arrays.asList(
                        new Car("1234", new BigDecimal("89.00"), TESLA,true),
                        new Car("5678", new BigDecimal("50.00"), AUDI, false),
                        new Car("5678", new BigDecimal("77.00"), MERCEDES, false)));
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getElectricCars() {
        // attempt to use streams
        return cars.stream().filter(car -> car.isElectric()).collect(Collectors.toList());
    }
}
