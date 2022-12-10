package com.inventory.inventory.businesslayer.services;

import com.inventory.inventory.businesslayer.entity.*;
import java.time.LocalDate;

public interface ClientCartService {
    void assignProductToClient(Product product, Client client);
    ClientCart getClientCart();
}
