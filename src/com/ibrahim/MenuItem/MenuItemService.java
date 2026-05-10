package com.ibrahim.MenuItem;


import com.ibrahim.Booking.BookingService;
import com.ibrahim.Car.CarService;
import com.ibrahim.User.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

import static com.ibrahim.Utils.DateHelper.promptForDate;
import static com.ibrahim.Utils.IDHelpers.promptForUUID;

public class MenuItemService {


    public static void getMenuItemPrompt() {

        for (MenuItem menuItem : MenuItemDao.getMenuItems()) {

            System.out.println(menuItem.getNumber() + " - " + menuItem.getDescription());
        }

    }


    public static String getMenuItemService(int userRequest, Scanner scanner) {
        String res = switch (userRequest) {

            case 1 -> {

                //Gathering inputs

                //Getting User Id
                UUID userId = promptForUUID(scanner, "What is the User Id");
                if (UserService.getUserById(userId) == null) {
                    yield "User doesn't exist.";

                }

                //Getting Car Id
                UUID carId = promptForUUID(scanner, "What is the Car Id");
                if (CarService.getCarById(carId) == null) {
                    yield("Car doesn't exist");

                }

                //Getting Start Date
                LocalDateTime startDate = promptForDate(scanner, "What is the start date? Use YYYY-MM-DD HH:mm format. Example: 2026-05-09 14:30");

                //Getting End Date
                LocalDateTime endDate = promptForDate(scanner, "What is the end date? Use YYYY-MM-DD HH:mm format. Example: 2026-05-09 14:30");
                if (endDate.isBefore(startDate)) {
                    yield("End date must be greater than start date");

                }


                yield BookingService.bookCar(carId, userId, startDate, endDate);
            }
            case 2 -> {

                //Getting User Id
                UUID userId = promptForUUID(scanner, "What is the User Id");
                if (UserService.getUserById(userId) == null) {
                    System.out.println("User doesn't exist");

                }

                yield BookingService.getUserBookedCars(userId);

            }
            case 3 -> BookingService.getActiveBookings();
            case 4 -> BookingService.getAvailableCars();
            case 5 -> BookingService.getAvailableElectricCars();
            case 6 -> UserService.getAllUsers();
            default -> "Unrecognized Input. Please Choose from the 7 above numbers!";
        };

        return res;
    }

}
