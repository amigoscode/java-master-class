package com.bennet.car.model;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarResponseMapper implements Function<Car, CarResponseDto> {
    @Override
    public CarResponseDto apply ( Car car ) {
        return new CarResponseDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getType()
        );
    }
}
