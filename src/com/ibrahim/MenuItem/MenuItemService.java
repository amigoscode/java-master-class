package com.ibrahim.MenuItem;


import com.ibrahim.Booking.BookingService;
import com.ibrahim.Car.CarService;
import com.ibrahim.User.UserService;


import java.util.Scanner;

public class MenuItemService {



    public static void getMenuItemPrompt(){

        for(MenuItem menuItem : MenuItemDao.getMenuItems()){

            System.out.println(menuItem.getNumber() + " - " + menuItem.getDescription());
        }

    }


    public static void handleMenuRequests(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            getMenuItemPrompt();
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

            String result = getMenuItemService(userRequest);
            System.out.println(result);
        }



    }



    private static String getMenuItemService(int userRequest) {
        String res = switch (userRequest) {

            case 1 -> {
                BookingService.addNewBooking();
                yield "Done";
            }
            case 2 -> {
                BookingService.getAllUserBookedCars();
                yield "Done";
            }
            case 3 -> {
                BookingService.getALLBookings();
                yield "Done";
            }
            case 4 -> {
                CarService.getAllCars();
                yield "Done";
            }
            case 5 -> {
                CarService.getAllAvailableElectricCars();
                yield "Done";
            }
            case 6 -> {
                UserService.getAllUsers();

                yield "Done";
            }
            default -> "Unrecognized Input. Please Choose from the 7 above numbers!";
        };

        return res;
    }

}
