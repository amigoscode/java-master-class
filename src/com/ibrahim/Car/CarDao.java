package com.ibrahim.Car;


import java.util.UUID;

public class CarDao {

    public static final Car[] cars;

    static {
        //populated these usin AI
        cars = new Car[]{
                new Car(UUID.fromString("11111111-1111-1111-1111-111111111111"), "Tesla Model 3",        2023, FuelType.ELECTRIC),
                new Car(UUID.fromString("22222222-2222-2222-2222-222222222222"), "Rivian R1T",           2024, FuelType.ELECTRIC),
                new Car(UUID.fromString("33333333-3333-3333-3333-333333333333"), "Toyota Camry",         2022, FuelType.GAS),
                new Car(UUID.fromString("44444444-4444-4444-4444-444444444444"), "Ford F-150",           2023, FuelType.GAS),
                new Car(UUID.fromString("55555555-5555-5555-5555-555555555555"), "Honda CR-V Hybrid",    2024, FuelType.HYBRID),
                new Car(UUID.fromString("66666666-6666-6666-6666-666666666666"), "Ford F-150 Lightning", 2024, FuelType.ELECTRIC),
        };
    }

    public static Car[] getCars() {
        return cars;
    }

}
