package com.ibrahim.Car;


import java.util.UUID;

public class Car {

    private UUID carId;
    private String name;
    private int year;
    private FuelType fuelType;

    //Wanted to do User but might be passing too much info
    private UUID bookingId;



    public Car(UUID carId, String name, int year, FuelType fuelType) {
        this.carId = carId;
        this.name = name;
        this.year = year;
        this.fuelType = fuelType;
        this.bookingId = null;
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }


    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", fuelType=" + fuelType +
                ", bookingId=" + bookingId +
                '}';
    }
}
