package com.ibrahim.Client;

import com.ibrahim.Booking.BookingDao;
import com.ibrahim.Booking.BookingService;
import com.ibrahim.Booking.CarBookingFileDataAccessService;
import com.ibrahim.Car.CarArrayDataAccessService;
import com.ibrahim.Car.CarDao;
import com.ibrahim.Car.CarService;
import com.ibrahim.MenuItem.IMenuItemDao;
import com.ibrahim.MenuItem.MenuItemArrayDataAccessService;
import com.ibrahim.MenuItem.MenuItemService;
import com.ibrahim.User.UserArrayDataAccessService;
import com.ibrahim.User.UserDao;
import com.ibrahim.User.UserService;

import java.util.Scanner;

public class Client {
    private final Scanner scanner = new Scanner(System.in);
    private final CarDao carDao = new CarArrayDataAccessService();
    private final CarService carService = new CarService(carDao);
    private final UserDao userDao = new UserArrayDataAccessService();
    private final UserService userService = new UserService(userDao);
    private final BookingDao bookingDao = new CarBookingFileDataAccessService("bookings.dat");
    //    private final BookingDao bookingDao = new CarBookingArrayDataAccessService();
    private final BookingService bookingService = new BookingService(bookingDao, carService, userService);
    private final IMenuItemDao menuItemDao = new MenuItemArrayDataAccessService();
    private final MenuItemService menuItemService = new MenuItemService(menuItemDao, carService, userService, bookingService);

    public void ConsoleRequest() {
        while (true) {
            menuItemService.getMenuItemPrompt();
            System.out.print("Enter choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }
            int userRequest = scanner.nextInt();
            scanner.nextLine();
            if (userRequest == 7) {
                System.out.println("Goodbye!");
                return;
            }
            String result = menuItemService.getMenuItemService(userRequest, scanner);
            System.out.println(result);
        }
    }
}
