package com.inventory.inventory.dao.implementation;
import com.inventory.inventory.businesslayer.entity.*;
import com.inventory.inventory.dao.services.ClientDaoService;
import com.inventory.inventory.utility.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDaoServiceImpl implements ClientDaoService {
    @Override
    public void addClient(Client client) {
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    @Override
    public void removeClient(Client client) {
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

    @Override
    public List<Client> AllClients() {
        List<Client> list = null;
        try(Session session = SessionUtil.getSession()){
            list =  session.createQuery("FROM Client",Client.class).list();
        }
        return list;
    }
}
