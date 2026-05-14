package com.ibrahim.Car;

import java.util.UUID;

public interface CarDao {
    Car[] getCars();
    Car findCarById(UUID carId);
}
