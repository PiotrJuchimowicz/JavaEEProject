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

import java.util.List;

//W warstwie wyzszej trzeba bedzie przetwarzac haslo na hasz oraz
// tworzyć powiązania np  dodawać danemu użytkownikowi zamówienie a zamówieniu użytkownika
//Do tej warstwy trafia obiekt który jest już gotowy do zapisu w bazie
public class TestMain {
    public static void main(String[] args) {

        BookDTO bookDTO = new BookDTO("1", "2", "3", BookDTO.rentalTime.SEVENDAYS, 10, null);
        UserDTO userDTO = new UserDTO("1", "2", "3", 1234, 12.00, UserDTO.Role.CLIENT, null);

        IssueDTO issueDTO = new IssueDTO(bookDTO, userDTO, null, null, null);

        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        bookJpaDAO.add(bookDTO);
        userJpaDAO.add(userDTO);
        issueJpaDAO.add(issueDTO);

        List<IssueDTO> list = issueJpaDAO.findIssuesOfThisBook(1);

        System.out.println(list.get(0).toString());


    }
}
