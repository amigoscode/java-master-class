package com.ibrahim.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateHelper {
    public static LocalDateTime promptForDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt + " (or 0 to cancel)");
            String input = scanner.nextLine().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime userDateInput;
            try {
                userDateInput = LocalDateTime.parse(input, formatter);

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time, use YYYY-MM-DD HH:mm format. Example: 2026-05-09 14:30");
                continue;
            }
            if (userDateInput.isBefore(LocalDateTime.now())) {
                System.out.println("Date must be ahead of current time");
                continue;
            }
            return userDateInput;
        }
    }
}

