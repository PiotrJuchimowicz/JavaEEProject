package com.company.project.Controllers;

import com.company.project.HibernateDAO.AuthoritiesHibernateDao;
import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.AuthoritiesJpaDao;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.AuthoritiesDTO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

       // UserDTO user = new UserDTO(AuthoritiesDTO.Role.ROLE_ADMIN,"username1",1,"Iwona", "Strubczewska", "iwona.strubczewska@gmail.com", "100320", 0);
       // userJpaDAO.add(user);



    }

    @RequestMapping("/issue")
    public void createIssue(){

        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();

        BookDTO bookDTO = bookJpaDAO.get(1);
        UserDTO userDTO = userJpaDAO.get(8);

        IssueDTO issueDTO = new IssueDTO(bookDTO,userDTO,null, LocalDateTime.now(), null);
        issueJpaDAO.add(issueDTO);

    }

    @RequestMapping("/userauth")
    @ResponseBody
    public String create(){
        UserDTO userDTO = new UserDTO("usteczka",1,"usteczkunik","usteczkunikis","usteczka@usteczkunikis.usti","usteczka",1250);
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        userJpaDAO.add(userDTO);

        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO(userDTO,AuthoritiesDTO.Role.ROLE_CLIENT);
        AuthoritiesJpaDao authoritiesJpaDao = new AuthoritiesHibernateDao();
        authoritiesJpaDao.add(authoritiesDTO);

        System.out.println(userJpaDAO.get(userDTO.getIdUser()).getAuthority());




        return "auth";
    }












}
