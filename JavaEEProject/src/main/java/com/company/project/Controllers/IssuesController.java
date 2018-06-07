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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssuesController {


    @Autowired
    private IssueJpaDAO issueJpaDAO;


    @RequestMapping("/remove/{id}")
    public void removeIssue(@PathVariable long id){
        issueJpaDAO.remove(issueJpaDAO.get(id));
    }

    @RequestMapping("/findbyid/{id}")
    @ResponseBody
    public void getById(@PathVariable long id, Model theModel){

        IssueDTO issue = issueJpaDAO.get(id);
        theModel.addAttribute(issue);

        //return issue;
    }

    @RequestMapping("/findall")
    @ResponseBody
    public void getAll(Model theModel){

        List<IssueDTO> lista = null;
        BookDTO book = new BookDTO("pokolenie black red white", "cd", "dramat", BookDTO.rentalTime.ONEDAY, 3, lista);
        BookHibernateDAO bdao = new BookHibernateDAO();
        bdao.add(book);

        UserDTO user = new UserDTO("ania", "dssds", "dsds@gmail.com", 121, 2.0, UserDTO.Role.CLIENT, lista);
        UserHibernateDAO udao = new UserHibernateDAO();
        udao.add(user);

        IssueDTO issue = new IssueDTO(book, user, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now() );
        IssueHibernateDAO idao = new IssueHibernateDAO();
        idao.add(issue);

        List<IssueDTO> list = issueJpaDAO.findAllIssues();
        theModel.addAllAttributes(list);
        System.out.println("lista: " + list);
        //return list;

    }

    @RequestMapping("/ofuser/{id)")
    @ResponseBody
    public List<IssueDTO> getUsersIssue(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findIssuesByThisUser(id);
        theModel.addAllAttributes(list);
        return list;
    }

    @RequestMapping("/ofbook/{id)") //id ksiazki
    @ResponseBody
    public List<IssueDTO> getBooksIssue(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findIssuesOfThisBook(id);
        theModel.addAllAttributes(list);
        return list;
    }

    @RequestMapping("/reservation")
    @ResponseBody
    public List<IssueDTO> getAllReservation( Model theModel){

        List<IssueDTO> list = issueJpaDAO.findAllReservations();
        theModel.addAllAttributes(list);
        return list;
    }

    @RequestMapping("/reservation/book/{id)") //id ksiazki
    @ResponseBody
    public List<IssueDTO> getBooksReservation(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findReservationsOfThisBook(id);
        theModel.addAllAttributes(list);
        return list;
    }

    @RequestMapping("/reservation/user/{id)") //id usera
    @ResponseBody
    public List<IssueDTO> getUsersReservation(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findReservationsByThisUser(id);
        theModel.addAllAttributes(list);
        return list;
    }






}
