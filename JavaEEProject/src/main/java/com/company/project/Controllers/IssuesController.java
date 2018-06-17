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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssuesController {



    //TODO UPORZĄDKOWAĆ NAZWY WIDOKÓW BO JEST SYF I SPRAWDZAĆ ZA KAŻDYM RAZEM CZY DZIAŁA, UPORZĄDKOWAĆ TEŻ NAZWY URL

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

//for user
@RequestMapping("/mine") //id usera
public String getUsersIssues( Model theModel) {

    long id = LoginController.getUserId();
    List<IssueDTO> issues = issueJpaDAO.findNotReturnedOfUser(id);

    if (issues.size()==0) {
        theModel.addAttribute("error", "Brak rezerwacji");
        return "issues-of-user";
    } else {
        theModel.addAttribute("issues", issues);
        return "issues-of-user";
    }
}

//for employee
    @RequestMapping("/ofuser")
    public String getUsersIssue(@RequestParam("id") long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findIssuesByThisUser(id);
        theModel.addAttribute("issues", issues);

        return "issues-of-user";
    }

//dziala
    @RequestMapping("/ofbook") //id ksiazki
    public String getBooksIssues(@RequestParam("id") long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findIssuesOfThisBook(id);
        theModel.addAttribute("issues", issues);

        return "issues-of-book";
    }


    //dziala
    @RequestMapping("/reservations")
    public String getAllReservation( Model theModel){

        List<IssueDTO> list = issueJpaDAO.findAllReservations();
        theModel.addAttribute("issues", list);
        return "issue-reservations";
    }

    @RequestMapping("/reservations/book/{id}") //id ksiazki
    public String getBooksReservation(@PathVariable long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findReservationsOfThisBook(id);
        theModel.addAttribute("issues", issues);
        return "reservations-of-book" ;
    }


    //for user
    @RequestMapping("/reservations/mine")
    public String getUsersReservation( Model theModel) {

        long id = LoginController.getUserId();
        List<IssueDTO> issues = issueJpaDAO.findReservationsByThisUser(id);

        if (issues.size()==0) {
            theModel.addAttribute("error", "Brak rezerwacji");
            return "issues-reservations-ofuser";
        } else {
            theModel.addAttribute("issues", issues);
            return "issues-reservations-ofuser";
        }
    }


    //for employee

    @RequestMapping("/reservations/ofuser")
    public String getUsersReserv(@RequestParam("id") long id, Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findReservationsByThisUser(id);
        theModel.addAttribute("issues", issues);

        return "issues-reservations-ofuser";
    }




    @RequestMapping("/reservations/findbyid/{id}")
    public String getOneReservation(@PathVariable long id, Model theModel){

        IssueDTO issue = issueJpaDAO.findOneReservation(id);
        theModel.addAttribute("issue", issue);

        return "issues-reservations-one";

    }

    @RequestMapping("/notreturned")
    public String findNotReturned(Model theModel){

        List<IssueDTO> issues = issueJpaDAO.findNotReturned();
        theModel.addAttribute("issues", issues);

        return "issues-not-returned";

    }


}


