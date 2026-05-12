package com.ibrahim.User;

import java.util.UUID;

public class UserService {

    private UserDao userDao = new UserDao();

    public User[] getAllUsers() {
        return userDao.getUsers();
    }

    public User getUserById(UUID userId) {
        for (User user : userDao.getUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}
