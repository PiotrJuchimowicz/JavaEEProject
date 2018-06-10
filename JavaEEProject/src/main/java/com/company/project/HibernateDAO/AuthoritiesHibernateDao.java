package com.company.project.HibernateDAO;

import com.company.project.Factory.JpaFactory;
import com.company.project.JpaDAO.AuthoritiesJpaDao;
import com.company.project.Models.AuthoritiesDTO;

import javax.persistence.EntityManager;

public class AuthoritiesHibernateDao implements AuthoritiesJpaDao {
    @Override
    public void add(AuthoritiesDTO a) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public void remove(AuthoritiesDTO a) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        a = em.merge(a);
        em.remove(a);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }

    @Override
    public AuthoritiesDTO get(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        AuthoritiesDTO a = em.find(AuthoritiesDTO.class, id);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return a;
    }

    @Override
    public void update(AuthoritiesDTO a) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }
}
