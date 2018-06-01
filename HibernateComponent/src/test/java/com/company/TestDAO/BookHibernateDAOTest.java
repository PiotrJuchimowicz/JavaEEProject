package com.company.TestDAO;

import com.company.HibernateDAO.BookHibernateDAO;
import com.company.JpaDAO.BookJpaDAO;
import com.company.Models.BookDTO;
import com.company.Models.IssueDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
//TODO
class BookHibernateDAOTest {

    private static BookJpaDAO bookDao;
    private static BookDTO book;


    @BeforeEach
    void setUp() {
        bookDao = new BookHibernateDAO();
        String title = "any Title";
        String author = "any Author";
        String category = "any Category";
        BookDTO.rentalTime rentalTime = BookDTO.rentalTime.SEVENDAYS;
        int numberOfCopies = 20;
        List<IssueDTO> issuesOfThisBook = null;

        book = new BookDTO(title, author, category, rentalTime, numberOfCopies, issuesOfThisBook);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addTest() {


    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}