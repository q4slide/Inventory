package com.inventory.inventory.dao.services;

import com.inventory.inventory.businesslayer.entity.User;

import java.util.List;

public interface UserDaoService {
    void addUser(User user);

    void removeUser(User user);

    List<User> allUsers();

    User login(String username, String password);
}
