package com.company.project;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {
/*

        BookDTO b1 = new BookDTO("1","2","3", BookDTO.rentalTime.SEVENDAYS,5);
        UserDTO u1 = new UserDTO("1","2","3",1,1, UserDTO.Role.ADMIN);
        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        bookJpaDAO.add(b1);
        userJpaDAO.add(u1);

        IssueDTO issueDTO = new IssueDTO(b1,u1,null,null,null);
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        issueJpaDAO.add(issueDTO);
*/

        /*
        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issueDTO = issueJpaDAO.get(1);

        BookDTO bookDTO = bookJpaDAO.get(1);
        System.out.println(bookDTO.getIssuesOfThisBook().get(0).getBook()           );
        //UserDTO userDTO = userJpaDAO.get(1);
*/
        //BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        //bookJpaDAO.remove(bookJpaDAO.get(19));

      //  UserJpaDAO userJpaDAO = new UserHibernateDAO();
        //userJpaDAO.remove(userJpaDAO.get(11));


        BookDTO bookDTO = new BookDTO("title", "author", "category", BookDTO.rentalTime.SEVENDAYS, 4);
        //UserDTO userDTO = new UserDTO("Name", "Surname", "email", 1, 1000.21, UserDTO.Role.ADMIN);


        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();

        bookJpaDAO.add(bookDTO);
       // userJpaDAO.add(userDTO);

        IssueDTO issueDTO;
        LocalDateTime reservationDate = LocalDateTime.of(2018, Month.APRIL,2,1,1);// data rezerwacji
        LocalDateTime issueDate = LocalDateTime.of(2018, Month.AUGUST,2,1,1);// data wydania egzemplarza
        LocalDateTime returnDate=LocalDateTime.of(2018, Month.AUGUST,10,1,1);//data zwrotu egzemplarza
       // issueDTO = new IssueDTO(bookDTO,userDTO,reservationDate,issueDate,returnDate);
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();

        //dodaje
      // issueJpaDAO.add(issueDTO);

        //test dla add i get


       // long idIssue = issueDTO.getIdIssue();
        long idBook = bookDTO.getIdBook();
      bookDTO.setAuthor("new AUTHOR");
      bookJpaDAO.update(bookDTO);
        //biore
        //IssueDTO afterIssue = issueJpaDAO.get(idIssue);
       // UserDTO afterUser = userJpaDAO.get(idUser);
        BookDTO afterBook = bookJpaDAO.get(idBook);

       // System.out.println(issueDTO);
       // System.out.println(afterIssue);

        System.out.println(bookDTO.getAuthor());
        System.out.println(bookDTO.getIdBook());
        System.out.println(afterBook.getIdBook());
        System.out.println(afterBook.getAuthor());
       // System.out.println(issueDTO.equals(afterIssue));
        //System.out.println(userDTO.equals(afterUser));


        //sprzatam
      // issueJpaDAO.remove(issueDTO);
      //  bookJpaDAO.remove(bookDTO);
      //  userJpaDAO.remove(userDTO);















    }
}
