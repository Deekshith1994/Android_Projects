package com.example.kdeek.inclass10;

/**
 * Created by kdeek on 11/7/2016.
 */
public class User {

    public String username;
    public String email;

    public User(String expenseName, String s, int cat, int cost) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
