package com.inventory.inventory.businesslayer.implementation;
import com.inventory.inventory.businesslayer.entity.*;
import com.inventory.inventory.businesslayer.services.ClientCartService;

import java.time.LocalDate;

public class ClientCartServiceImpl implements ClientCartService {
    private  ClientCart clientCart;
    public  void assignProductToClient(Product product,Client client){
         clientCart = new ClientCart(client,product,getLocalDate());
    }
    private LocalDate getLocalDate(){
        return LocalDate.now();
    }
    public  ClientCart getClientCart() {
        return clientCart;
    }
}
