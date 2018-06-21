package com.company.project.Controllers;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JDBC.JdbcImplementation;
import com.company.project.JDBC.JdbcInterface;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.lang.String;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private static final Logger logger = LogManager.getLogger(BooksController.class);

    @Autowired
    private BookJpaDAO bookJpaDAO;

    @RequestMapping("/removeconfirm/{id}") // widok pośredni między szczegółami książki a usunięciem
    public String removeConfirm(@PathVariable String id, Model theModel) {
        BookDTO book = bookJpaDAO.get(Long.parseLong(id));
        theModel.addAttribute("book", book);
        logger.info("Usienieto ksiazke");
        return "book-remove-confirm";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable String id) {
        bookJpaDAO.remove(bookJpaDAO.get(Long.parseLong(id)));
        logger.info("Potwierdzenie usuniecia");
        return "redirect:/books/getall";
    }

    @ModelAttribute("rentalTimes")
    public BookDTO.rentalTime[] frequencies() {
        return BookDTO.rentalTime.values();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAddPage(Model m) {
        m.addAttribute("book", new BookDTO());
        logger.info("Wczytano strone do dodawania ksiazki");
        return "book-add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") BookDTO bookDTO, Model theModel) {

        if (bookDTO.getAuthor() == null || bookDTO.getTitle() == null || bookDTO.getCategory() == null || bookDTO.getRentalTime() == null || bookDTO.getNumberOfCopies() == 0) {
            theModel.addAttribute("error", "Wszystkie pola muszą być wypełnione");
            logger.error("Nie wypełniono wszystkich pól ");
            return "book-add";
        } else {
            theModel.addAttribute("confirm", "Dodano książkę");
            bookJpaDAO.add(bookDTO);
            logger.info("Dodano ksiazke");
            return "book-add";
        }
    }


    @RequestMapping(value = "/getall")
    public String getAll(Model theModel) {
        List<BookDTO> list = bookJpaDAO.findAllBooks();
        theModel.addAttribute("books", list);


        logger.info("Pobrano wszystkie ksiazki");

        return "books-get-all";
    }

    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable String id, Model theModel) {
        BookDTO book = bookJpaDAO.get(Long.parseLong(id));
        logger.info("Pobrano jedna ksiazke");
        theModel.addAttribute("book", book);
        return "book-get-one";
    }

    @RequestMapping("/findbycategory") // Z: ten widok służy do wybierania kategorii po której będzemy szukać
    public String findByCategory() {
        return "book-find-category";
    }

    @RequestMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, Model theModel) {
        List<BookDTO> books = bookJpaDAO.findByCategory(category);
        theModel.addAttribute("books", books);
        logger.info("pobrano ksiazki po kategorii");
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
        logger.info("pobrano ksiazki autora");
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
        //biore id
        long userId = LoginController.getUserId();  //METODA ZWRACAJACA ID USERA KTORY JEST OBECNIE ZALOGOWANY
        //pobieram usera z bazki
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        UserDTO user = userJpaDAO.get(userId);
        logger.info("pobrano rezerwacje");
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
            logger.error("Nie mozna wypozyczyc ksiazki bo jej nie ma ");
            return "book-get-one";
        }


    }

    @RequestMapping("/borrow/{id}")
    public String borrowBook(@PathVariable long id, Model theModel) {


        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issue = issueJpaDAO.get(id);
        logger.info("wypozyczenie ksiazki");
        if (issue.getIssueDate() != null) {
            theModel.addAttribute("issue", issue);
            theModel.addAttribute("borrowError", "Ta książka została już wypożyczona.");
            logger.error("Nie mozna wypożyczyc tej samej ksiazki dwa razy");
        } else {
            theModel.addAttribute("issue", issue);
            theModel.addAttribute("borrowConfirm", "Wypożyczono książkę.");
            issue.setIssueDate(LocalDateTime.now());
            issueJpaDAO.update(issue);
            logger.info("wypozyczono");
        }
        return "issues-reservations-one";
    }

    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable long id, Model theModel) {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        IssueDTO issue = issueJpaDAO.get(id);
        BookDTO book = bookJpaDAO.get(issue.getBook().getIdBook());
        logger.info("zwror ksiazki");
        if (issue.getReturnDate() != null) {

            theModel.addAttribute("issue", issue);
            theModel.addAttribute("returnError", "Ta książka została już wcześniej zwrócona.");
            logger.error("nie zwrocono ksiazki blad");
            return "issue-get-one";
        } else {
            book.setNumberOfCopies(book.getNumberOfCopies() + 1);
            issue.setReturnDate(LocalDateTime.now());
            issueJpaDAO.update(issue);
            bookJpaDAO.update(book);
            logger.info("zwrocono");
            theModel.addAttribute("issue", issue);
            theModel.addAttribute("confirm", "Zwrócono książkę");
            return "issue-get-one";
        }
    }

    //TODO
    @RequestMapping("/find")
    public String findByParameters(@RequestParam("p") String param, Model theModel) {


        JdbcInterface instance = new JdbcImplementation();

        List<BookDTO> titleList = null;
        List<BookDTO> authorList = null;
        List<BookDTO> categoryList = null;
        logger.info("wyszukanie po wpisanym parametrze");

      //  if (param != null) {
            List<BookDTO> total = null;
            titleList = instance.titleBookWithRegularExpression(param);
            authorList = instance.authorBooksWithRegularExpression(param);
            categoryList = instance.categoryBookWithRegularExpression(param);

            total = new LinkedList<>(titleList);

            for (BookDTO book : authorList) {
                if (!total.contains(book))
                    total.add(book);
            }

            for(BookDTO book : categoryList){
                if(!total.contains(book))
                    total.add(book);
            }

            theModel.addAttribute("books", total);
            return "books-get-all";

      //  } else {
       //     theModel.addAttribute("error", "Musisz wypełnić to pole aby wyszukać");
       //     return "books-get-all";
        }




}
