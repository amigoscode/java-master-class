package com.bennet.car.repository;

import com.bennet.car.model.Car;
import com.bennet.car.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByType(CarType type);
}
