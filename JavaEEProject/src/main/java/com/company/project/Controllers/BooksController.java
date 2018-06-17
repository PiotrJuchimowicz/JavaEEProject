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
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.ArrayList;
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
    public String removeBook(@PathVariable String id) {
        bookJpaDAO.remove(bookJpaDAO.get(Long.parseLong(id)));
        return "redirect:/books/getall";
    }

    @ModelAttribute("rentalTimes")
    public BookDTO.rentalTime[] frequencies() {
        return BookDTO.rentalTime.values();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAddPage(Model m) {
        //BookDTO book = new BookDTO();
        m.addAttribute("book", new BookDTO());
        return "book-add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") BookDTO bookDTO,  Model theModel) {

        if ( bookDTO.getAuthor()==null || bookDTO.getTitle()==null || bookDTO.getCategory()==null || bookDTO.getRentalTime()==null || bookDTO.getNumberOfCopies() == 0 ) {
            theModel.addAttribute("error", "Wszystkie pola muszą być wypełnione");
            return "book-add";
        } else {
            theModel.addAttribute("confirm", "Dodano książkę");
            bookJpaDAO.add(bookDTO);
            return "book-add";
        }
    }


    //Metoda z walidacją - jak zadziała można zmienić
    /*@RequestMapping(value="/add", method=RequestMethod.POST)
    public String submitAdd (@Valid BookDTO book, Model model, BindingResult bindingResult ){
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

    @RequestMapping(value = "/getall")
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
    // Z: Mój mockup do wybierania z istniejących kategorii
    // przyadłoby się zrobić SELECTA który wybiera istniejące w bazie kategorie
    // jak się da to posortować go alfabetycznie
    @ModelAttribute("categories")
    public List<String> categories() {
        //List<String> c = BookJpaDAO.getCategories(); o takie coś mogło by być
        List<String> c = new ArrayList<>();
        c.add("śmieszne");
        c.add("dramat");
        c.add("komedia");
        return c;
    }

    @RequestMapping("/findbycategory") // Z: ten widok służy do wybierania kategorii po której będzemy szukać
    public String findByCategory() {
        return "book-find-category";
    }

    @RequestMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, Model theModel) {
        List<BookDTO> books = bookJpaDAO.findByCategory(category);
        theModel.addAttribute("books", books);
        theModel.addAttribute("category", category); //Z: dodałam żeby można było w tytule napisać jaka to kategoria
        return "book-get-category";
    }


    // Z: Mój mockup do wybierania z istniejących autorów
    // przyadłoby się zrobić SELECTA który wybiera istniejących w bazie autorów
    // jak się da to posortować go alfabetycznie
    @ModelAttribute("authors")
    public List<String> authors() {
        //List<String> a = BookJpaDAO.getAuthors(); o takie coś mogło by być
        List<String> a = new ArrayList<>();
        a.add("Fredro");
        a.add("Żaneta");
        a.add("Juliusz");
        return a;
    }

    @RequestMapping("/findbyauthor") // Z: ten widok służy do wybierania autora po którym będziemy szukać
    public String findByAuthor() {
        return "book-find-author";
    }


    @RequestMapping("/author/{author}")
    public String getByAuthor(@PathVariable String author, Model theModel) {
        List<BookDTO> books = bookJpaDAO.findByAuthor(author);
        theModel.addAttribute("books", books);
        theModel.addAttribute("author", author);
        return "book-get-author";
    }


    // Tytuły nie znajdują się, jeżeli ejst wpisana tylko część. Musi być wpisany cały tytuł.
    @RequestMapping("/title") //Z: Musiałam usunąć parametr, bo był problem z odczytywaniem go z formularza
    public String getByTitle(Model theModel, @RequestParam("t") String title) {
        List<BookDTO> books = bookJpaDAO.findByTitle(title);
        theModel.addAttribute("books", books);
        theModel.addAttribute("title", title); //Z: dodałam żeby móc wyświetlić to w tytule strony
        return "book-get-title";
    }


    @RequestMapping("/reservation/{id}")
    public String bookReservation(@PathVariable long id, Model theModel) {
//TODO update number of copies !!!!!!
// TODO ensure username unique !!!!!! (method)
//TODO panel użytkownika jakis, zmienianie jego danych itp, jego wypożyczenia, nie wiem co jeszcze ?
        //biore id
        long userId = LoginController.getUserId();  //METODA ZWRACAJACA ID USERA KTORY JEST OBECNIE ZALOGOWANY
        //pobieram usera z bazki
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        UserDTO user = userJpaDAO.get(userId);

        //biore id ksiazki i pobieram ksiazke z bazki
        BookDTO book = bookJpaDAO.get(id);
        //odejmuje książke żeby nikt inny nie mógł zarezerwować ( o ile jeszcze jakaś jest)

        if (book.getNumberOfCopies() > 0) {

            theModel.addAttribute("book", book);
            theModel.addAttribute("confirm", "Książka zarezerwowana.");
            book.setNumberOfCopies(book.getNumberOfCopies() - 1);

            bookJpaDAO.update(book);

            //robie issue i dodaje do bazki
            IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
            IssueDTO issueDTO = new IssueDTO(book, user, LocalDateTime.now(), null, null);
            issueJpaDAO.add(issueDTO);

            return "book-get-one";
        } else {
            theModel.addAttribute("book", book);
            theModel.addAttribute("ReservationError", "Ta książka jest już niedostępna.");
            return "book-get-one";
        }


    }

    @RequestMapping("/borrow/{id}")
    public String borrowBook(@PathVariable long id, Model theModel) {


        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issue = issueJpaDAO.get(id);

        if (issue.getIssueDate() != null) {
            theModel.addAttribute("issue", issue);
            theModel.addAttribute("borrowError", "Ta książka została już wypożyczona.");
        } else {
            theModel.addAttribute("issue", issue);
            theModel.addAttribute("borrowConfirm", "Wypożyczono książkę.");
            issue.setIssueDate(LocalDateTime.now());
            issueJpaDAO.update(issue);
        }
        return "issues-reservations-one";
    }

    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable long id, Model theModel) {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issue = issueJpaDAO.get(id);
        BookDTO book = bookJpaDAO.get(issue.getBook().getIdBook());

        if (issue.getReturnDate() != null) {

            theModel.addAttribute("issue", issue);
            theModel.addAttribute("returnError", "Ta książka została już wcześniej zwrócona.");
            return "issue-get-one";
        } else {
            book.setNumberOfCopies(book.getNumberOfCopies() + 1);
            issue.setReturnDate(LocalDateTime.now());
            issueJpaDAO.update(issue);
            bookJpaDAO.update(book);

            theModel.addAttribute("issue", issue);
            theModel.addAttribute("confirm", "Zwrócono książkę");
            return "issue-get-one";
        }
    }


}
