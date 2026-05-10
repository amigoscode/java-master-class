package com.ibrahim.Client;

import java.util.Scanner;

import static com.ibrahim.MenuItem.MenuItemService.getMenuItemPrompt;
import static com.ibrahim.MenuItem.MenuItemService.getMenuItemService;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void ConsoleRequest(){

        while(true){
            getMenuItemPrompt();
            System.out.print("Enter choice: ");

            if (!scanner .hasNextInt()) {
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

            String result = getMenuItemService(userRequest, scanner);
            System.out.println(result);
        }

    }




}
