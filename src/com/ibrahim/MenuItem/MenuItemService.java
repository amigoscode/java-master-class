package com.ibrahim.MenuItem;

import com.ibrahim.Booking.BookingService;
import com.ibrahim.Car.CarService;
import com.ibrahim.User.UserService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import static com.ibrahim.Utils.DateHelper.promptForDate;
import static com.ibrahim.Utils.IDHelpers.promptForUUID;

public class MenuItemService {
    private final IMenuItemDao menuItemDao;
    private final CarService carService;
    private final UserService userService;
    private final BookingService bookingService;

    public MenuItemService(IMenuItemDao menuItemDao, CarService carService, UserService userService, BookingService bookingService) {
        this.menuItemDao = menuItemDao;
        this.carService = carService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    public void getMenuItemPrompt() {
        for (MenuItem menuItem : menuItemDao.getMenuItems()) {
            System.out.println(menuItem.getNumber() + " - " + menuItem.getDescription());
        }
    }

    public String getMenuItemService(int userRequest, Scanner scanner) {
        Object res = switch (userRequest) {
            case 1 -> {
                //Getting User Id
                UUID userId = promptForUUID(scanner, "What is the User Id");
                if (userService.findUserById(userId) == null) {
                    yield "User doesn't exist.";

                }
                //Getting Car Id
                UUID carId = promptForUUID(scanner, "What is the Car Id");
                if (carService.findCarById(carId) == null) {
                    yield ("Car doesn't exist");

                }
                //Getting Start Date
                LocalDateTime startDate = promptForDate(scanner, "What is the start date? Use YYYY-MM-DD HH:mm format. Example: 2026-05-09 14:30");
                //Getting End Date
                LocalDateTime endDate = promptForDate(scanner, "What is the end date? Use YYYY-MM-DD HH:mm format. Example: 2026-05-09 14:30");
                if (endDate.isBefore(startDate)) {
                    yield ("End date must be greater than start date");

                }
                yield bookingService.bookCar(carId, userId, startDate, endDate).toString();
            }
            case 2 -> {
                //Getting User Id
                UUID userId = promptForUUID(scanner, "What is the User Id");
                if (userService.findUserById(userId) == null) {
                    System.out.println("User doesn't exist");
                }
                yield Arrays.toString(bookingService.getUserBookedCars(userId));
            }
            case 3 -> Arrays.toString(bookingService.getActiveBookings());
            case 4 -> Arrays.toString(bookingService.getAvailableCars());
            case 5 -> Arrays.toString(bookingService.getAvailableElectricCars());
            case 6 -> Arrays.toString(userService.getAllUsers());
            default -> "Unrecognized Input. Please Choose from the 7 above numbers!";
        };
        return res.toString();
    }

}
