package com.stan.user;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUserById(UUID userId) {
        List<User> users = getUsers();
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }
}
