package com.company.project.Controllers;

import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import com.company.project.Models.authorities;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/create")
public class CreateController {

    @RequestMapping("/book")
    public void createBook(){

        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        BookDTO book = new BookDTO("Tytul","Iwona", "kryminal", BookDTO.rentalTime.SEVENDAYS,3);

        bookJpaDAO.add(book);
    }

    @RequestMapping("/user")
    public void createUser(){

        UserJpaDAO userJpaDAO = new UserHibernateDAO();

       // UserDTO user = new UserDTO(authorities.Role.ADMIN,"username1",1,"Iwona", "Strubczewska", "iwona.strubczewska@gmail.com", "100320", 0);
       // userJpaDAO.add(user);



    }

    @RequestMapping("/issue")
    public void createIssue(){

        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();

        BookDTO bookDTO = bookJpaDAO.get(1);
        UserDTO userDTO = userJpaDAO.get(1);

        IssueDTO issueDTO = new IssueDTO(bookDTO,userDTO,null, LocalDateTime.now(), null);
        issueJpaDAO.add(issueDTO);

    }
}
