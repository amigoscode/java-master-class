package com.ibrahim.User;

import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    //private Booking[] bookings;
    private UUID userId;

    public User(UUID uuid, String firstname) {
        this.userId = uuid;
        this.firstName = firstname;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
