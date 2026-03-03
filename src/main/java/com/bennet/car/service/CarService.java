package com.bennet.car.service;

import com.bennet.booking.model.BookingResponseDto;
import com.bennet.booking.service.BookingService;
import com.bennet.car.model.Car;
import com.bennet.car.model.CarRequestDto;
import com.bennet.car.model.CarRequestMapper;
import com.bennet.car.model.CarResponseDto;
import com.bennet.car.model.CarResponseMapper;
import com.bennet.car.model.CarType;
import com.bennet.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final BookingService bookingService;
    private final CarResponseMapper carResponseMapper;
    private final CarRequestMapper carRequestMapper;

    public List<CarResponseDto> getAllCars () {
        log.info("Fetching all cars");
        return carRepository.findAll().stream().map( carResponseMapper ).toList();
    }

    public List<CarResponseDto> getAllElectricCars () {
        return carRepository.findByType(CarType.ELECTRIC).stream().map( carResponseMapper ).toList();
    }

    public List<CarResponseDto> getAllGasolineCars () {
        return carRepository.findByType(CarType.GASOLINE).stream().map( carResponseMapper ).toList();
    }

    public List<CarResponseDto> getAllHybridCars () {
        return carRepository.findByType(CarType.HYBRID).stream().map( carResponseMapper ).toList();
    }

    public List<CarResponseDto> getAllAvailableCars () {
        return carRepository.findAll().stream()
                .filter( car -> bookingService.isCarAvailable(car.getId()) )
                .map( carResponseMapper )
                .toList();
    }

    public List<CarResponseDto> getAllAvailableElectricCars () {
        return carRepository.findByType(CarType.ELECTRIC).stream()
                .filter( car -> bookingService.isCarAvailable(car.getId()) )
                .map( carResponseMapper )
                .toList();
    }

    public List<CarResponseDto> getAllAvailableGasolineCars () {
        return carRepository.findByType(CarType.GASOLINE).stream()
                .filter( car -> bookingService.isCarAvailable(car.getId()) )
                .map( carResponseMapper )
                .toList();
    }

    public List<CarResponseDto> getAllAvailableHybridCars () {
        return carRepository.findByType(CarType.HYBRID).stream()
                .filter( car -> bookingService.isCarAvailable(car.getId()) )
                .map( carResponseMapper )
                .toList();
    }

    public List<CarResponseDto> getAllUserBookedCars ( UUID userId ) {
        List<UUID> bookedCarIds = bookingService.getBookingsByUserId(userId).stream()
                .map( BookingResponseDto::carId )
                .toList();
        
        return carRepository.findAllById(bookedCarIds).stream()
                .map( carResponseMapper )
                .toList();
    }

    public CarResponseDto getCarById ( UUID carId ) {
        return carRepository.findById(carId)
                .map( carResponseMapper )
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
    }

    public CarResponseDto createCar ( CarRequestDto carRequestDto ) {
        Car car = carRepository.save( carRequestMapper.apply( carRequestDto ) );
        return carResponseMapper.apply( car );
    }
}
