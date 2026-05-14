package com.ibrahim.User;

import java.util.UUID;

public interface UserDao {
    User[] getUsers();
    User findUserById(UUID userId);
}
