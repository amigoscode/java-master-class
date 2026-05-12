package com.ibrahim.Utils;

import java.util.Scanner;
import java.util.UUID;

public class IDHelpers {
    public static UUID promptForUUID(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt + " (or 0 to cancel)");
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                return null;
            }
            try {
                return UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid ID, must be a UUID. Example: 123e4567-e89b-12d3-a456-426614174000");
            }
        }
    }
}