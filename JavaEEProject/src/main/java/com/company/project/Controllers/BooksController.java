package com.company.project.Controllers;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
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
    @ResponseBody
    public List<BookDTO> getByCategory(@PathVariable String category, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByCategory(category);
        theModel.addAllAttributes(books);
        return books;
    }

    @RequestMapping("/author/{author}")
    @ResponseBody
    public List<BookDTO> getByAuthor(@PathVariable String author, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByAuthor(author);
        theModel.addAllAttributes(books);
        return books;
    }

    @RequestMapping("/title/{title}")
    @ResponseBody
    public List<BookDTO> getByTitle(@PathVariable String title, Model theModel){
        List<BookDTO> books = bookJpaDAO.findByTitle(title);
        theModel.addAllAttributes(books);
        return books;
    }
}
