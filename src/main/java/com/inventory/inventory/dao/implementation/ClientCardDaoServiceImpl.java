package com.inventory.inventory.dao.implementation;
import com.inventory.inventory.businesslayer.entity.*;

import com.inventory.inventory.dao.services.ClientCartDaoService;
import com.inventory.inventory.utility.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class ClientCardDaoServiceImpl implements ClientCartDaoService {

    @Override
    public void addClientCart(ClientCart clientCart) {
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(clientCart);
            transaction.commit();
        }
    }

    @Override
    public void removeFromClientCart(ClientCart clientCart) {
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(clientCart);
            transaction.commit();
        }
    }



    @Override
    public List<ClientCart> getByClient(Client client) {
        List<ClientCart> clientCartList = null;
        try(Session session = SessionUtil.getSession()){
            Query<ClientCart> query = session.createQuery("FROM ClientCart c WHERE c.client =:id",ClientCart.class);
            query.setParameter("id",client);
            clientCartList=query.list();
        }
        return clientCartList;
    }
}
