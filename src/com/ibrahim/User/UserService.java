package com.ibrahim.User;

import java.util.UUID;

public class UserService {

    public static void getAllUsers(){

        for(User user : UserDao.getUsers()){

            System.out.println(user.toString());
        }

    }

    public static User getUserById(UUID userId){

        for(User user : UserDao.getUsers()){

            if (user.getUserId().equals(userId)){
                return user;
            }
        }
        return null;
    }

}
