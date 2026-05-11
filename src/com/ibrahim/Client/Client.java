package com.ibrahim.Client;

import com.ibrahim.MenuItem.MenuItemService;

import java.util.Scanner;


public class Client {
    private final Scanner scanner = new Scanner(System.in);
    private final MenuItemService menuItemService = new MenuItemService();

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
