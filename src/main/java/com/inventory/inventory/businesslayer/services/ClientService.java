package com.inventory.inventory.businesslayer.services;


import com.inventory.inventory.businesslayer.entity.Client;

public interface ClientService {
    void addClient(String fName,String lName,String email,String phoneNumber);
    Client getClient();
}
