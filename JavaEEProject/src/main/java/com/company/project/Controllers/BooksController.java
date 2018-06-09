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
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.lang.String;

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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

/*
    @RequestMapping(value="/add", method=RequestMethod.POST)
<<<<<<< HEAD
    public String submitAdd( BookDTO book, Model m) {
        BookHibernateDAO bdao = new BookHibernateDAO();

        // tu sprawdzenie czy wszystko ok z książką
        int ifSthWrong = 0; //inkrementacja jeżeli coś jest źle
        if(book.getAuthor().length()<3) ifSthWrong++;
        if(book.getTitle().length()<3) ifSthWrong++;
        if(book.getCategory().length()<3) ifSthWrong++;
        if(book.getRentalTime() != BookDTO.rentalTime.ONEDAY
                && book.getRentalTime() != BookDTO.rentalTime.SEVENDAYS
                && book.getRentalTime() != BookDTO.rentalTime.THREEMONTHS) ifSthWrong++;
        if(book.getNumberOfCopies() <= 0) ifSthWrong++;

        //jeśli tak to
        if( ifSthWrong == 0 ) {
            bdao.add(book);
            m.addAttribute("message", "Dodanie książki przebiegło pomyślnie.");
        }
        //jeśli nie to
        else m.addAttribute("message", "Dodanie książki nie udało się.");
        return "book-add-response";
    }
  
  
    //Metoda z walidacją - jak zadziała można zmienić
    /*@RequestMapping(value="/add", method=RequestMethod.POST)
    public String submitAdd (@Valid BookDTO book, Model model, BindingResult bindingResult ){
=======
    public String submitAdd (@Valid BookDTO book,BindingResult bindingResult, Model model ){
>>>>>>> 0f7b36e4d0ec9b64c10c939111aee84003f1b093
        BookHibernateDAO bookDAO = new BookHibernateDAO();
        bookDAO.add(book);
        //m.addAttribute("message", "Successfully saved book: " + book.toString());

        if(bindingResult.hasErrors())
        {
           // model.addAttribute("Error", "Pole login/hasło nie moze być puste.");
            return "book-add";

        }else {
            return "confirmation";
        }
    }*/

    @RequestMapping(value="/getall")
    public String getAll(Model theModel) {
        List<BookDTO> list = bookJpaDAO.findAllBooks();
        theModel.addAttribute("books", list);
        return "books-get-all";
    }

    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable String id, Model theModel) {
        BookDTO book = bookJpaDAO.get(Long.parseLong(id));
        theModel.addAttribute("book", book);
        return "book-get-one";
    }

       /*
    @RequestMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByCategory(category);
        theModel.addAttribute("books", books);
        return "book-get-category";
    }
    */


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

        UserJpaDAO userJpaDAO = new UserHibernateDAO();
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
