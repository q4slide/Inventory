package com.inventory.inventory.dao.services;
import com.inventory.inventory.businesslayer.entity.*;

import java.util.List;

public interface ClientCartDaoService {
    void addClientCart(ClientCart clientCart);
    void removeFromClientCart(ClientCart clientCart);
    List<ClientCart> getByClient(Client client);
}
