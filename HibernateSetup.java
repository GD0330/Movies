package database_layer;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSetup {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load configuration and build session factory
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
