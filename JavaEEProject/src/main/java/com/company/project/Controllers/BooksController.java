package com.company.project.Controllers;


import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookJpaDAO bookJpaDAO;




    @RequestMapping("/remove/{id}")
    public void removeBook(@PathVariable long id){
        bookJpaDAO.remove(bookJpaDAO.get(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addBook(HttpServletRequest request , Model theModel) {

        try {
            String title = request.getParameter("bookTitle");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String rent = request.getParameter("rentalTime");
            BookDTO.rentalTime rentalTime = BookDTO.rentalTime.valueOf(rent); // przy tym pewnie bedzie wyjatek
            Integer numberOfCopies = Integer.parseInt(request.getParameter("numberOfCopies"));
            List<IssueDTO> list = null;

            BookDTO book = new BookDTO(title, author, category, rentalTime, numberOfCopies, list);
            bookJpaDAO.add(book);
            theModel.addAttribute(book);


        } catch(Exception e){
            //jakas obsluga
        }

    }


    //zwraca liste wszystkich ksiazek
    @RequestMapping("/getall")
    @ResponseBody
    public List<BookDTO> getAll(Model theModel){

        List<BookDTO> list = bookJpaDAO.findAllBooks();
        theModel.addAttribute(list);

        return list;
    }

    @RequestMapping("/findbyid/{id}")
    @ResponseBody
    public BookDTO getById(@PathVariable long id, Model theModel){

       BookDTO book = bookJpaDAO.get(id);
       theModel.addAttribute(book);
       return book;

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
