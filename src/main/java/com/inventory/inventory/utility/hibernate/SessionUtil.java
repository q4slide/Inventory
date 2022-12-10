package com.inventory.inventory.utility.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {
    private static SessionFactory sessionFactory = null;

    static {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
