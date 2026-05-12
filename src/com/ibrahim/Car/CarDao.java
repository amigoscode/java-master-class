package com.ibrahim.Car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {

    private static final Car[] cars;

     static{
        cars = new Car[]{
                new Car(UUID.randomUUID(), "Tesla Model 3", 2023, PowerType.ELECTRIC, BigDecimal.valueOf(100)),
                new Car(UUID.randomUUID(), "Rivian R1T", 2024, PowerType.ELECTRIC, BigDecimal.valueOf(200)),
                new Car(UUID.randomUUID(), "Toyota Camry", 2022, PowerType.GAS, BigDecimal.valueOf(201)),
                new Car(UUID.randomUUID(), "Ford F-150", 2023, PowerType.GAS, BigDecimal.valueOf(220)),
                new Car(UUID.randomUUID(), "Honda CR-V Hybrid", 2024, PowerType.HYBRID, BigDecimal.valueOf(210)),
                new Car(UUID.randomUUID(), "Ford F-150 Lightning", 2024, PowerType.ELECTRIC, BigDecimal.valueOf(220)),
        };
    }

    public Car[] getCars() {
        return cars;
    }

}
