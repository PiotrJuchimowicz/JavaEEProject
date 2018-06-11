package com.company.project.Controllers;


import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    BookJpaDAO bookJpaDAO;

    @RequestMapping("/")
    public String goToAllBook(Model theModel){
        bookJpaDAO.findAllBooks();
        theModel.addAttribute("books", bookJpaDAO.findAllBooks());

        return "redirect:/books/getall";
    }

    @GetMapping("/loginPage")
    public String loginPage(){

        return "login-page";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(){

        return "access-denied";
    }

    public static long getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username

        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        List<UserDTO> list = userJpaDAO.findAllUsers();
        for( UserDTO user : userJpaDAO.findAllUsers())
        {
            if(user.getUsername().equals(username)) return user.getIdUser();

        }
        return Long.parseLong(null);



    }


}
