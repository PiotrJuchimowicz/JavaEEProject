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
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookJpaDAO bookJpaDAO;

    @RequestMapping("/removeconfirm/{id}") // widok pośredni między szczegółami książki a usunięciem
    public String removeConfirm(@PathVariable String id, Model theModel) {
        BookDTO book = bookJpaDAO.get(Long.parseLong(id));
        theModel.addAttribute("book", book);
        return "book-remove-confirm";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable String id){
        bookJpaDAO.remove(bookJpaDAO.get(Long.parseLong(id)));
        return "redirect:/books/getall";
    }

    @ModelAttribute("rentalTimes")
    public BookDTO.rentalTime[] frequencies() {
        return BookDTO.rentalTime.values();
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String loadAddPage(Model m) {
        BookDTO book = new BookDTO();
        m.addAttribute("book", book);
        return "book-add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String submitAdd (@Valid BookDTO book, Model model, BindingResult bindingResult ){
        BookHibernateDAO bookDAO = new BookHibernateDAO();
        bookDAO.add(book);
        //m.addAttribute("message", "Successfully saved book: " + book.toString());

        if(bindingResult.hasErrors())
        {
            return "error";

        }else {
            return "confirmation";
        }
    }

    @RequestMapping(value="/getall")
    public String getAll(Model theModel) {

        List<BookDTO> list = bookJpaDAO.findAllBooks();
        theModel.addAttribute("books", list);
        return "books-get-all";

    }

    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable String id, Model theModel){
       BookDTO book = bookJpaDAO.get(Long.parseLong(id));
       theModel.addAttribute("book", book);
       return "book-get-one";

    }

    @RequestMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByCategory(category);
        theModel.addAttribute("books", books);
        return "book-get-category";

    }

    @RequestMapping("/author/{author}")
    public String getByAuthor(@PathVariable String author, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByAuthor(author);
        theModel.addAttribute("books", books);
        return "book-get-author";

    }

    @RequestMapping("/title/{title}")
    public String getByTitle(@PathVariable String title, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByTitle(title);
        theModel.addAttribute("books", books);
        return "book-get-title";

    }

    @RequestMapping("/{id}/reservation")
    public String bookReservation(@PathVariable String id){

        UserJpaDAO userJpaDAO = new UserHibernateDAO(); //nie wiem czy nie trzeba wstrzyknąć
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();

        BookDTO book = bookJpaDAO.get(Long.parseLong(id));
        UserDTO user = userJpaDAO.get(1); //jeszcze tego nie umiem pobrać ale ogarne

        IssueDTO issueDTO = new IssueDTO(book,user,LocalDateTime.now(),null , null);
        issueJpaDAO.add(issueDTO);

        return "book-reservation";
    }

    @RequestMapping("/{id}/hire")
    public String bookHire(@PathVariable String id){

        //trzeba sprawdzić czy książka była wcześniej zarezerwowana
        //najlepiej poszukać jakoś po id
        //potrzebna metoda sprawdzająca czy osoba wypożyczająca daną książkę
        //wcześniej ją zarezerwowała

        //czyli czy istnieje już issue tej książki dla tej osoby z reservationDate not null


        return "book-hire";
    }


}
