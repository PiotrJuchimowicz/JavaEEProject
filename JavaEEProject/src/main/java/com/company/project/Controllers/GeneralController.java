package com.company.project.Controllers;

import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
public class GeneralController {

    @Autowired
    private UserJpaDAO userJpaDAO;


    //tu potrzebny jakiś wyjatek również
    @RequestMapping("/login")
    public void logIn(HttpServletRequest request, Model theModel){

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        login.substring(1);
        Long id = Long.parseLong(login);

        UserDTO user = userJpaDAO.get(id);
    }
/*
    @RequestMapping("/signup")
    public void signUp(HttpServletRequest request, Model theModel) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String login = request.getParameter("login"); //TO MUSI BYĆ NUMER DOKUMENTU
        char role = login.charAt(0);
        //TU PORÓ
        Double payment = 0.0;
        UserDTO.Role userRole = UserDTO.Role.valueOf(role); // przy tym pewnie bedzie wyjatek
        List<IssueDTO> list = null;

        //TU TRZEBA WRZUCIĆ METODE DO ROBIENIA HASŁA , tymczasowo hasło 1;
        int password = 1;
        UserDTO user = new UserDTO(name,surname,email,password,payment, userRole, list);
        userJpaDAO.add(user);
        theModel.addAttribute(user);

    }*/

}

//public char charAt(int index)