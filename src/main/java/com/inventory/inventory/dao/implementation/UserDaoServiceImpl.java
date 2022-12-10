package com.inventory.inventory.dao.implementation;

import com.inventory.inventory.businesslayer.entity.User;
import com.inventory.inventory.dao.services.UserDaoService;
import com.inventory.inventory.utility.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoServiceImpl implements UserDaoService {
    @Override
    public void addUser(User user) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUser(User user) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> allUsers() {
        List<User> user = null;
        try (Session session = SessionUtil.getSession()) {
            user = session.createQuery("FROM User", User.class).list();
        }
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        for (User u : allUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password))
                user = u;

        }
        return user;
    }
}
