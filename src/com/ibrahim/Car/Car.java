package com.ibrahim.Car;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Car implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID carId;
    private String name;
    private int year;
    private PowerType powerType;
    private String regNumber;
    private BigDecimal rentalPricePerDay;

    public Car(UUID carId, String name, int year, PowerType powerType, BigDecimal rentalPricePerDay) {
        this.carId = carId;
        this.name = name;
        this.year = year;
        this.powerType = powerType;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(carId, car.carId) && Objects.equals(name, car.name) && powerType == car.powerType && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPricePerDay, car.rentalPricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, name, year, powerType, regNumber, rentalPricePerDay);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", powerType=" + powerType +
                ", regNumber='" + regNumber + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                '}';
    }
}






