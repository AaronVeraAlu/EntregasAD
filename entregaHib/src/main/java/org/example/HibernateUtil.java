package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory factory;
    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration().configure().
                    buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        }
        return factory;
    }
}