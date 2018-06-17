package com.company.project;


import com.company.project.Factory.JpaFactory;
import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.JpaDAO.*;
import com.company.project.Models.*;
import com.company.project.HibernateDAO.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {
/*
       BookDTO bookDTO = new BookDTO("tit","autor","kategoria",BookDTO.rentalTime.SEVENDAYS,10);
        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        bookJpaDAO.add(bookDTO);



        UserDTO  userDTO= new UserDTO("user",1,"name","surname","email","password",0);
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        userJpaDAO.add(userDTO);

        AuthoritiesDTO authority = new AuthoritiesDTO(userJpaDAO.get(userDTO.getIdUser()),AuthoritiesDTO.Role.ROLE_CLIENT);
        AuthoritiesJpaDao authoritiesJpaDao = new AuthoritiesHibernateDao();
        authoritiesJpaDao.add(authority);

        IssueDTO issueDTO = new IssueDTO(bookJpaDAO.get(bookDTO.getIdBook()),userJpaDAO.get(userDTO.getIdUser()),LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now());

        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        issueJpaDAO.add(issueDTO);

/*

      EntityManager em = JpaFactory.getEntityManager();
        em.close();
       JpaFactory.closeEntityManagerFactory();

/*
        UserDTO userDTO = new UserDTO("usteczka",1,"usteczkunik","usteczkunikis","usteczka@usteczkunikis.usti","usteczka",1250);
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        userJpaDAO.add(userDTO);

        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO(userDTO,AuthoritiesDTO.Role.ROLE_CLIENT);
        AuthoritiesJpaDao authoritiesJpaDao = new AuthoritiesHibernateDao();
        authoritiesJpaDao.add(authoritiesDTO);

        System.out.println(userJpaDAO.get(userDTO.getIdUser()).getAuthority().getAuthority());
  */
    }
}
