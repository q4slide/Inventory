package com.inventory.inventory.dao.implementation;

import com.inventory.inventory.businesslayer.entity.Product;
import com.inventory.inventory.dao.services.ProductDaoService;
import com.inventory.inventory.utility.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoServiceImpl implements ProductDaoService {
    @Override
    public void addProduct(Product product) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    @Override
    public void removeProduct(Product product) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }

    @Override
    public List<Product> productList() {
        List<Product> list = null;
        try (Session session = SessionUtil.getSession()) {
            list = session.createQuery("From Product", Product.class).list();
        }
        return list;
    }

    @Override
    public List<Product> onlyLtta() {
        List<Product> list;
        try (Session session = SessionUtil.getSession()) {
            Query<Product> query = session.createQuery("from Product p where p.ltta=:ltta", Product.class);
            query.setParameter("ltta", true);
            list = query.list();
        }
        return list;
    }

    @Override
    public void updateProduct(Product product) {
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }
}
