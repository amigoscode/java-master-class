package com.ibrahim.Car;





import java.util.*;

public class CarService {


    //all avaible cars
    public static void getAllCars(){

        for(Car car : CarDao.cars){

            System.out.println(car.toString());
        }

    }

    //all avaiable user cars

//Decided to go through booking rather than Car directly
//    public static void getAllUserBookedCars(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("What is the UserId of the Car owner");
//        String userID = scanner.nextLine();
//
//        List<Car> userCars = new ArrayList<>();
//
//        for(Car car: CarDao.getCars()){
//            if (car.getCurrentBookingId().toString().equals(userID)){
//                userCars.add(car);
//            }
//        }
//
//        for(Car car : userCars){
//
//            System.out.println(car.getName());
//        }
//
//
//
//

    //all avaiable user cars
    public static void getAllAvailableElectricCars(){


        List<Car> electricCars = new ArrayList<>();

        for(Car car: CarDao.getCars()){
            if (car.getFuelType()== FuelType.ELECTRIC){
                electricCars.add(car);
            }
        }

        for(Car car : electricCars){

            System.out.println(car.toString());
        }



    }

    //get car by car Id
    public static Car getCarById(UUID carId){

        for(Car car : CarDao.getCars()){

            if (car.getCarId().equals(carId)){
                if (car.getFuelType() == FuelType.ELECTRIC){
                    return car;
                }
            }
        }
        return null;
    }


}
