package com.company.project.Controllers;

import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.AuthoritiesDTO;
import com.company.project.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserJpaDAO userJpaDAO;


    @RequestMapping("/removeconfirm/{id}") //Z: widok pośredni między szczegółami użytkownika a usunięciem
    public String removeConfirm(@PathVariable long id, Model theModel) {
        UserDTO user = userJpaDAO.get(id);
        theModel.addAttribute("user", user);
        return "user-remove-confirm";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {

        userJpaDAO.remove(userJpaDAO.get(id));

        return "redirect:/users/findall";
    }

  // tu trzeba zmienić na AuthoritiesDTO
  //Z: Służy do stworzenia listy do wybierania opcji
    @ModelAttribute("roles")
    public AuthoritiesDTO.Role[] roles(){
        return AuthoritiesDTO.Role.values();
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
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


    @RequestMapping("/userspanel")
    public String usersPanel(Model theModel){

        long id = LoginController.getUserId(); //pobiera id z aktualnej sesji
        UserDTO user = userJpaDAO.get(id);

        theModel.addAttribute("user", user);

        return "users-panel";
    }

    @RequestMapping(value = "/userspanel/edit", method = RequestMethod.GET)
    public String editData(Model theModel){

        theModel.addAttribute("user", new UserDTO());

        return "users-edit";
    }

    @RequestMapping(value = "/userspanel/edit", method = RequestMethod.POST)
    public String editDateConfirm(@Valid @ModelAttribute("user") UserDTO userDTO,  Model theModel){

        long id = LoginController.getUserId();
        userDTO = userJpaDAO.get(id);
        theModel.addAttribute("confirm", "Dodano książkę");
        userJpaDAO.update(userDTO);


        return "users-edit";
    }





}
