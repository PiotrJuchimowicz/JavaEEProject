package com.company.project.Controllers;

import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserJpaDAO userJpaDAO;



    @RequestMapping("/remove/{id}")
    public void removeUser(@PathVariable long id){
        userJpaDAO.remove(userJpaDAO.get(id));
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String loadAddPage(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "user-add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String submitAdd (@Valid UserDTO user, Model model, BindingResult bindingResult ){
        UserHibernateDAO userDAO = new UserHibernateDAO();
        userDAO.add(user);
        if(bindingResult.hasErrors())
        {
            return "error";

        }else {
            return "confirmation";
        }
    }



    /*
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addBook(HttpServletRequest request , Model theModel) {

        try {

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            Double payment = Double.parseDouble(request.getParameter("payment"));
            UserDTO.Role userRole = UserDTO.Role.valueOf(role); // przy tym pewnie bedzie wyjatek
            List<IssueDTO> list = null;

            //TU TRZEBA WRZUCIĆ METODE DO ROBIENIA HASŁA , tymczasowo hasło 1;
            int password = 1;
            UserDTO user = new UserDTO(name,surname,email,password,payment, userRole, list);
            userJpaDAO.add(user);
            theModel.addAttribute(user);

        } catch(Exception e){
            //jakas obsluga
        }

    }
    */

    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable long id, Model theModel){
        UserDTO user = userJpaDAO.get(id);
        theModel.addAttribute("user", user);
        return "user-get-one";
    }

    @RequestMapping("/findall")
    public String getAll(Model theModel){
        List<UserDTO> list = userJpaDAO.findAllUsers();
        theModel.addAttribute("users", list);

        return "users-get-all";
    }






}
