package com.amigoscode;

import com.amigoscode.booking.CarBooking;
import com.amigoscode.booking.CarBookingDao;
import com.amigoscode.booking.CarBookingService;
import com.amigoscode.car.Car;
import com.amigoscode.car.CarDAO;
import com.amigoscode.car.CarService;
import com.amigoscode.user.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserFakerDataAccessService();
        UserService userService = new UserService(userDao);

        CarBookingDao carBookingDao = new CarBookingDao();
        CarDAO carDAO = new CarDAO();

        CarService carService = new CarService(carDAO);
        CarBookingService carBookingService = new CarBookingService(carBookingDao, carService);

        Scanner scanner = new Scanner(System.in);

        boolean keepLooping = true;

        while (keepLooping) {
            try {
                displayMenu();
                String choice = scanner.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> bookCar(userService, carBookingService, scanner);
                    case 2 -> displayAllUserBookedCars(userService, carBookingService, scanner);
                    case 3 -> allBookings(carBookingService);
                    case 4 -> displayAvailableCars(carBookingService, false);
                    case 5 -> displayAvailableCars(carBookingService, true);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepLooping = false;
                    default -> System.out.println(choice + " not a valid option ❌");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void allBookings(CarBookingService carBookingService) {
        List<CarBooking> bookings = carBookingService.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available 😕");
            return;
        }
        for (CarBooking booking : bookings) {
            System.out.println("booking = " + booking);
        }
    }

    private static void displayAllUsers(UserService userService) {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("❌ No users in the system");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void displayAvailableCars(CarBookingService carBookingService, boolean isElectric) {
        List<Car> availableCars = isElectric ? carBookingService.getAvailableElectricCars() : carBookingService.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("❌ No cars available for renting");
            return;
        }
        for (Car availableCar : availableCars) {
            System.out.println(availableCar);
        }
    }

    private static void displayAllUserBookedCars(UserService userService,
                                                 CarBookingService carBookingService,
                                                 Scanner scanner) {
        displayAllUsers(userService);

        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        User user = userService.getUserById(UUID.fromString(userId));
        if (user == null) {
            System.out.println("❌ No user found with id " + userId);
            return;
        }

        List<Car> userBookedCars = carBookingService.getUserBookedCars(user.getId());
        if (userBookedCars.isEmpty()) {
            System.out.printf("❌ user %s has no cars booked", user);
            return;
        }
        for (Car userBookedCar : userBookedCars) {
            System.out.println(userBookedCar);
        }
    }

    private static void bookCar(UserService userService, CarBookingService carBookingService, Scanner scanner) {
        displayAvailableCars(carBookingService, false);

        System.out.println("➡️ select car reg number");
        String regNumber = scanner.nextLine();

        displayAllUsers(userService);

        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id " + userId);
            } else {
                UUID bookingId = carBookingService.bookCar(user, regNumber);
                String confirmationMessage = """
                        🎉 Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(regNumber, user, bookingId);
                System.out.println(confirmationMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("""
                \n
                1️⃣ - Book Car
                2️⃣ - View All User Booked Cars
                3️⃣ - View All Bookings
                4️⃣ - View Available Cars
                5️⃣ - View Available Electric Cars
                6️⃣ - View all users
                7️⃣ - Exit
                """);
    }

}