package com.company.project.Controllers;

import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserJpaDAO userJpaDAO;



    @RequestMapping("/remove/{id}")
    public void removeUser(@PathVariable long id){
        userJpaDAO.remove(userJpaDAO.get(id));
    }

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



    @RequestMapping("/findbyid/{id}")
    @ResponseBody
    public UserDTO getById(@PathVariable long id, Model theModel){
        UserDTO user = userJpaDAO.get(id);
        theModel.addAttribute(user);

        return user;
    }

    @RequestMapping("/findall")
    @ResponseBody
    public List<UserDTO> getAll(Model theModel){
        List<UserDTO> list = userJpaDAO.findAllUsers();
        theModel.addAllAttributes(list);

        return list;
    }






}
