package com.ibrahim.User;

import java.util.Arrays;
import java.util.UUID;

public class UserService {

    public static String getAllUsers() {
        return Arrays.toString(UserDao.getUsers());
    }

    public static User getUserById(UUID userId) {
        for (User user : UserDao.getUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }


}
