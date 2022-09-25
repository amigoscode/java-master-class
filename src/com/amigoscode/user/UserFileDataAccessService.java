package com.amigoscode.user;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class UserFileDataAccessService implements UserDao {

    @Override
    public User[] getUsers() {
        File file = new File("src/com/amigoscode/users.csv");

        /*
            Size 4 because I know there are 4 entries in src/users.csv
            If you add more rows in the file update the size of the initial array too
        */
        User[] users = new User[4];

        // read example
        try {
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(",");
                users[index] = new User(UUID.fromString(split[0]), split[1]);
                index++;
            }
            return users;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
