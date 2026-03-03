package com.bennet.car.controller;

import com.bennet.car.model.CarRequestDto;
import com.bennet.car.model.CarResponseDto;
import com.bennet.car.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> createCar( @Valid @RequestBody CarRequestDto carRequestDto ) {
        CarResponseDto dto = carService.createCar(carRequestDto);
        return ResponseEntity.status( HttpStatus.CREATED )
                .location( URI.create( "/cars/" + dto.id()) )
                .body( dto);
    }

    @GetMapping
    public List<CarResponseDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/available")
    public List<CarResponseDto> getAllAvailableCars() {
        return carService.getAllAvailableCars();
    }

    @GetMapping("/electric")
    public List<CarResponseDto> getAllElectricCars() {
        return carService.getAllElectricCars();
    }

    @GetMapping("/gasoline")
    public List<CarResponseDto> getAllGasolineCars() {
        return carService.getAllGasolineCars();
    }

    @GetMapping("/hybrid")
    public List<CarResponseDto> getAllHybridCars() {
        return carService.getAllHybridCars();
    }
}
