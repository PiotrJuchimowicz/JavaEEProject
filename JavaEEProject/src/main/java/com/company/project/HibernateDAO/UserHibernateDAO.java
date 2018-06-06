package com.company.project.HibernateDAO;
import com.company.project.Factory.JpaFactory;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

//Przed dodaniem wypożyczenia danemu użytkownikowi ten użytkownik musi znajdować się w bazie - inacze będzie brzydki wyjątek

@Component
@Repository
@Transactional
public class UserHibernateDAO implements UserJpaDAO {

    //Dodaje użytkownika u, który jest wcześniej stworzony
    @Override
    public void add(UserDTO u) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    //Zwraca użytkownika o danym id albo null jesli nie ma takiego
    @Override
    public UserDTO get(long id) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        UserDTO u = em.find(UserDTO.class, id);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return u;
    }

    //Aktualizuje uzytkownika u. Wczesniej nalezy pobrac tego uzytkownika za pomoca metody get
    //a nastepnie zmienic setterem odpowiednie pole i wtedy dac tego uzytkownika do ponizszej metody
    @Override
    public void update(UserDTO u) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();
    }

    //Usuwa uzytkownika ktory zostaje podany do tej metody. Na poczatku trzeba tego uzytkownika pobrac z
    //bazy za pomoca metody get
    @Override
    public void remove(UserDTO u) {
        EntityManager em = JpaFactory.getEntityManager();
        em.getTransaction().begin();
        u = em.merge(u);
        em.remove(u);
        em.getTransaction().commit();
        em.close();
        JpaFactory.closeEntityManagerFactory();

    }


    @Override
    public List<UserDTO> findAllUsers() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<UserDTO> query = em.createNamedQuery("findAllUsers", UserDTO.class);
        List<UserDTO> list = query.getResultList();
        em.close();
        JpaFactory.closeEntityManagerFactory();

        return list;
    }


}
