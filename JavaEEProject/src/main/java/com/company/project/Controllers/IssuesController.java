package com.company.project.Controllers;



import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssuesController {


    @Autowired
    private IssueJpaDAO issueJpaDAO;

//dziala!
    @RequestMapping("/removeconfirm/{id}") // widok pośredni między szczegółami książki a usunięciem
    public String removeConfirm(@PathVariable String id, Model theModel) {
        IssueDTO issue = issueJpaDAO.get(Long.parseLong(id));
        theModel.addAttribute("issue", issue);
        return "issues-remove-confirm";
    }
//dziala!
    @RequestMapping("/remove/{id}")
    public String removeIssue(@PathVariable long id){
        issueJpaDAO.remove(issueJpaDAO.get(id));
        return "redirect:/issues/findall";
    }

//działa
    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable long id, Model theModel){

        IssueDTO issue = issueJpaDAO.get(id);
        BookDTO bookDTO = issue.getBook();

        theModel.addAttribute("issue", issue);

        return "issue-get-one";


    }

//zrobione
    @RequestMapping("/findall")
    public String getAll(Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findAllIssues();
        theModel.addAttribute("issues", issues);

        return "issues-get-all";

    }


    @RequestMapping("/ofuser/{id)")
    @ResponseBody
    public List<IssueDTO> getUsersIssue(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findIssuesByThisUser(id);
        theModel.addAllAttributes(list);
        return list;
    }

//dziala
    @RequestMapping("/ofbook/{id}") //id ksiazki
    public String getBooksIssues(@PathVariable long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findIssuesOfThisBook(id);
        theModel.addAttribute("issues", issues);

        return "issues-of-book";
    }


    //dziala
    @RequestMapping("/reservation")
    public String getAllReservation( Model theModel){

        List<IssueDTO> list = issueJpaDAO.findAllReservations();
        theModel.addAttribute("issues", list);
        return "issue-reservations";
    }

    @RequestMapping("/reservation/book/{id}") //id ksiazki
    public String getBooksReservation(@PathVariable long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findReservationsOfThisBook(id);
        theModel.addAttribute("issues", issues);
        return "reservations-of-book" ;
    }

    @RequestMapping("/reservation/user/{id}") //id usera
    @ResponseBody
    public List<IssueDTO> getUsersReservation(@PathVariable long id, Model theModel){

        List<IssueDTO> list = issueJpaDAO.findReservationsByThisUser(id);
        theModel.addAllAttributes(list);
        return list;
    }






}
