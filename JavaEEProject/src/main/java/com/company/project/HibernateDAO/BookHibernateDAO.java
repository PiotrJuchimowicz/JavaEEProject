package com.company.project.HibernateDAO;

import com.company.project.Factory.JpaFactory;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.Models.BookDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

//Przed dodaniem wypożyczenia danej książki dana książka musi być w bazie - inaczej rzuci wyjątek

@Component
@Repository
@Transactional
public class BookHibernateDAO implements BookJpaDAO {

    //dodaje książkę b,ktora musi byc wczesniej utworzona i podana do ponizszej metody.
    //Zwraca id dodanego właśnie rekordu
    @Override
    public void add( BookDTO b) {

        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
        em.close();

        JpaFactory.closeEntityManagerFactory();


    }

    //zwraca książkę o danym id,jeśli brak to zwraca null
    @Override
    public BookDTO get(long id) {

        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        BookDTO b = em.find(BookDTO.class, id);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return b;
    }


    //Aktualizuje rekord b w bazie. Na początku trzeba pobrać b za pomocą get i dac go do tej metody jako argument
    @Override
    public void update(BookDTO b) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(b);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }


    //Usuwa rekord b z bazy. Na początku trzeba pobrac b za pomocą get i dac go do tej metody jako argument
    @Override
    public void remove(BookDTO b) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        b = em.merge(b);
        em.remove(b);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }


    @Override
    public List<BookDTO> findAllBooks() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<BookDTO> query = em.createNamedQuery("findAllBooks", BookDTO.class);
        List<BookDTO> result = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return result;
    }

    @Override
    public List<BookDTO> findByCategory(String category) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<BookDTO> query = em.createNamedQuery("findByCategory", BookDTO.class);
        query.setParameter("category", category);
        List<BookDTO> result = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return result;

    }

    @Override
    public List<BookDTO> findByAuthor(String author) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<BookDTO> query = em.createNamedQuery("findByAuthor", BookDTO.class);
        query.setParameter("author", author);
        List<BookDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<BookDTO> query = em.createNamedQuery("findByTitle", BookDTO.class);
        query.setParameter("title", title);
        List<BookDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }


}
