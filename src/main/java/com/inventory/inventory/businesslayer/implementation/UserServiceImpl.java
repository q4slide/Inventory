package com.inventory.inventory.businesslayer.implementation;


import com.inventory.inventory.businesslayer.entity.User;
import com.inventory.inventory.businesslayer.services.UserService;

public class UserServiceImpl implements UserService {
    private User user;
    @Override
    public void addUser(String username, String password, Boolean isAdmin) {
        user = new User(username,password,isAdmin);
    }

    public User getUser() {
        return user;
    }
}
