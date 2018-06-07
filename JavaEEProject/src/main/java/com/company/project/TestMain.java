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
        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issueDTO = issueJpaDAO.get(1);

        BookDTO bookDTO = bookJpaDAO.get(1);
        System.out.println(bookDTO.getIssuesOfThisBook().get(0).getBook()           );
        //UserDTO userDTO = userJpaDAO.get(1);

















    }
}
