package com.inventory.inventory.businesslayer.services;

import com.inventory.inventory.businesslayer.entity.User;

public interface UserService {
    void addUser(String username,String password,Boolean isAdmin);
    User getUser();
}
