package com.inventory.inventory.dao.services;

import com.inventory.inventory.businesslayer.entity.*;
import java.util.List;

public interface ClientDaoService {
    void addClient(Client client);
    void removeClient(Client client);
    List<Client> AllClients();
}
