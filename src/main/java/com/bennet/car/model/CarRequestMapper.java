package com.bennet.car.model;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarRequestMapper implements Function<CarRequestDto, Car> {
    @Override
    public Car apply ( CarRequestDto carRequestDto ) {
        return new Car(
                carRequestDto.brand(),
                carRequestDto.model(),
                carRequestDto.type()
        );
    }
}
