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

import java.util.List;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {
/*
        BookDTO bookDTO = new BookDTO("1", "2", "3", BookDTO.rentalTime.SEVENDAYS, 10, null);
        UserDTO userDTO = new UserDTO("1", "2", "3", 1234, 12.00, UserDTO.Role.CLIENT, null);

        IssueDTO issueDTO = new IssueDTO(bookDTO, userDTO, null, null, null);

        BookJpaDAO bookJpaDAO = (BookJpaDAO) new BookHibernateDAO();
        UserJpaDAO userJpaDAO = (UserJpaDAO) new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        bookJpaDAO.add(bookDTO);
        userJpaDAO.add(userDTO);
        issueJpaDAO.add(issueDTO);

        List<IssueDTO> list = issueJpaDAO.findIssuesOfThisBook(1);

        System.out.println(list.get(0).toString());
*/

    }
}
