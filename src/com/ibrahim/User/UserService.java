package com.ibrahim.User;

import java.util.UUID;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User[] getAllUsers() {
        return userDao.getUsers();
    }

    public User findUserById(UUID userId) {
        User user = userDao.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        return user;
    }

}
