package com.company.Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaFactory {
    private static JpaFactory instance;
    private EntityManagerFactory emf;

    private JpaFactory() {
        emf = Persistence.createEntityManagerFactory("persistence");
    }

    private static JpaFactory getInstance() {
        if (instance == null) {
            instance = new JpaFactory();
        }
        return instance;
    }

    public static EntityManager getEntityManager() {
        return getInstance().emf.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (instance != null) {
            instance.emf.close();
            instance = null;
        }
    }
}
