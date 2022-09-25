package com.amigoscode.user;

import java.util.UUID;

public class UserService {
    private final UserDao userDao = new UserFileDataAccessService();

    public User[] getUsers() {
        return userDao.getUsers();
    }

    public User getUserById(UUID id) {
        for (User user : getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
