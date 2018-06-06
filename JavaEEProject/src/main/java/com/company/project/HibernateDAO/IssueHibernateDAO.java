package com.company.project.HibernateDAO;


import com.company.project.Factory.JpaFactory;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.Models.IssueDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Repository
@Transactional
public class IssueHibernateDAO implements IssueJpaDAO {
    //Dodaje wypożyczenie do bazy
    //Zamowienie to musi byc wczesniej stworzone(musi byc juz dodana osoba ktora je wypozycza oraz
    //wypozyczany egzemplarz).Tak samo tej osobie musi byc dodane zamowienie oraz tej ksiazce
    //musi byc dodane zamowienie.
    @Override
    public void add(IssueDTO i) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }

    //Zwraca to zamowienie o danym id lub null jesli takiego zamowienia nie ma
    @Override
    public IssueDTO get(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        IssueDTO i = em.find(IssueDTO.class, id);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return i;
    }

    @Override
    public void update(IssueDTO i) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(i);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public void remove(IssueDTO i) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        i = em.merge(i);
        em.remove(i);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    @Override
    public List<IssueDTO> findIssuesOfThisBook(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findIssuesOfThisBook", IssueDTO.class);
        query.setParameter("id", id);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<IssueDTO> findIssuesByThisUser(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findIssuesByThisUser", IssueDTO.class);
        query.setParameter("id", id);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<IssueDTO> findAllIssues() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findAllIssues", IssueDTO.class);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<IssueDTO> findReservationsOfThisBook(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findReservationsOfThisBook", IssueDTO.class);
        query.setParameter("id", id);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<IssueDTO> findReservationsByThisUser(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findReservationsByThisUser", IssueDTO.class);
        query.setParameter("id", id);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<IssueDTO> findAllReservations() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<IssueDTO> query = em.createNamedQuery("findAllReservations", IssueDTO.class);
        List<IssueDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }


}
