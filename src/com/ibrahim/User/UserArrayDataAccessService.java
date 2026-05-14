package com.ibrahim.User;

import java.util.UUID;

public class UserArrayDataAccessService implements UserDao{

    private static final User[] users;

    static {
        users = new User[]{new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James")};
    }

    public User[] getUsers() {
        return users;
    }

    @Override
    public User findUserById(UUID userId) {
        for (User user : getUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}