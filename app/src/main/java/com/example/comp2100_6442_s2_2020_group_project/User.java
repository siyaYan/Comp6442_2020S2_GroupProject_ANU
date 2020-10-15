package com.example.comp2100_6442_s2_2020_group_project;

import android.content.Context;

import java.util.ArrayList;

public class User {
    public String id;
    public String userName;
    public String password;
    public String[] records;

    public boolean User(String userName, String password, ArrayList<User> users) {
        User user=searchUser(userName, password, users);
        if (user!= null) {
            this.userName = userName;
            this.password = password;
            this.id = user.id;
            return true;
        } else {
            System.out.println("don't have the user,please registered！");
            return false;
        }
    }

    public User searchUser(String userName,String password,ArrayList<User> users) {
        for (User user : users) {
            if (user.userName.matches(userName) &
                    user.password.matches((password))) {
                return user;
            }
        }
        return null;
    }
}
