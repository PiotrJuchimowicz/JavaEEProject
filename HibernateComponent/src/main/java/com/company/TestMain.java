package com.company;

import com.company.HibernateDAO.BookHibernateDAO;
import com.company.HibernateDAO.IssueHibernateDAO;
import com.company.HibernateDAO.UserHibernateDAO;
import com.company.JpaDAO.BookJpaDAO;
import com.company.JpaDAO.IssueJpaDAO;
import com.company.JpaDAO.UserJpaDAO;
import com.company.Models.BookDTO;
import com.company.Models.IssueDTO;
import com.company.Models.UserDTO;
import java.time.LocalDateTime;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {

    BookJpaDAO bookJpaDAO = new BookHibernateDAO();
    IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
    UserJpaDAO userJpaDAO = new UserHibernateDAO();

      BookDTO bookDTO =  bookJpaDAO.get(1);
        System.out.println(bookDTO);
        UserDTO userDTO = userJpaDAO.get(1);
        System.out.println(userDTO);

        IssueDTO issueDTO = new IssueDTO(bookDTO,userDTO,null,null, LocalDateTime.now());
        issueJpaDAO.add(issueDTO);
    }
}
