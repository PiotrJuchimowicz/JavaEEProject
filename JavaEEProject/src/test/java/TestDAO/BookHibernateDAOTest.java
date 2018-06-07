package TestDAO;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import java.awt.print.Book;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//TODO
class BookHibernateDAOTest {


    @Test
    void CRUDTest() {

        BookJpaDAO bookDao = new BookHibernateDAO();
        String title = "any Title";
        String author = "any Author";
        String category = "any Category";
        BookDTO.rentalTime rentalTime = BookDTO.rentalTime.SEVENDAYS;
        int numberOfCopies = 20;


        BookDTO book = new BookDTO(title, author, category, rentalTime, numberOfCopies);
        bookDao.add(book);

        long id = book.getIdBook();

        BookDTO bookAfter = bookDao.get(id);

        //sprawdza czy mozna dodac a potem pobrac ta ksiazke
        assert bookAfter.equals(book);


        //Sprawdza czy mozna uaktualnic ksiazke
        book.setAuthor("other Author");
        bookDao.update(book);

        assert bookDao.get(id).getAuthor().equals("other Author");

        //sprawdza czy mozna uzusnac ksiazke

        bookDao.remove(bookDao.get(id));
        assert bookDao.get(id) == null;

    }

    @Test
    void findAllBooksTest() {
        BookJpaDAO bookDao = new BookHibernateDAO();
        BookDTO b1, b2, b3;
        b1 = new BookDTO("1", "1", "1", BookDTO.rentalTime.THREEMONTHS, 1);
        b2 = new BookDTO("2", "2", "2", BookDTO.rentalTime.ONEDAY, 2);
        b3 = new BookDTO("3", "3", "3", BookDTO.rentalTime.SEVENDAYS, 3);

        //pomocnicza lista
        List<BookDTO> supplementaryList = new LinkedList<>();
        supplementaryList.add(b1);
        supplementaryList.add(b2);
        supplementaryList.add(b3);


        //dodaje ksiazki
        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);

        //bierze ksiazki z bazy
        List<BookDTO> result = bookDao.findAllBooks();
        //jesli listy sa rowne to  musza miec rowniez elementy w tej samej kolejnosci
        //sortowanko :
        Comparator<BookDTO> comparator = Comparator.comparingLong(BookDTO::getIdBook);
        result.sort(comparator);


        //assercja
        assertTrue(result.equals(supplementaryList));


        //sprzatamy
        bookDao.remove(b1);
        bookDao.remove(b2);
        bookDao.remove(b3);


    }

    @Test
    void findByAuthorTest() {
        BookJpaDAO bookDao = new BookHibernateDAO();
        BookDTO b1 = new BookDTO("title1", "author1", "category1", BookDTO.rentalTime.SEVENDAYS, 10);
        BookDTO b2 = new BookDTO("title2", "author1", "category2", BookDTO.rentalTime.ONEDAY, 2);
        BookDTO b3 = new BookDTO("title3", "author1", "category3", BookDTO.rentalTime.THREEMONTHS, 1);
        BookDTO b4 = new BookDTO("title4", "author2", "category4", BookDTO.rentalTime.SEVENDAYS, 0);

        List<BookDTO> supplementaryList = new LinkedList<>();
        supplementaryList.add(b1);
        supplementaryList.add(b2);
        supplementaryList.add(b3);

        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);
        bookDao.add(b4);

        List<BookDTO> result = bookDao.findByAuthor("author1");
        Comparator<BookDTO> comparator = Comparator.comparingLong(BookDTO::getIdBook);
        result.sort(comparator);


        assertTrue(result.equals(supplementaryList));


        bookDao.remove(b1);
        bookDao.remove(b2);
        bookDao.remove(b3);
        bookDao.remove(b4);
    }

    @Test
    void findByCategoryTest() {
        BookJpaDAO bookDao = new BookHibernateDAO();
        BookDTO b1 = new BookDTO("title1", "author1", "category1", BookDTO.rentalTime.SEVENDAYS, 10);
        BookDTO b2 = new BookDTO("title2", "author2", "category1", BookDTO.rentalTime.ONEDAY, 2);
        BookDTO b3 = new BookDTO("title3", "author3", "category1", BookDTO.rentalTime.THREEMONTHS, 1);
        BookDTO b4 = new BookDTO("title4", "author4", "category2", BookDTO.rentalTime.SEVENDAYS, 0);

        List<BookDTO> supplementaryList = new LinkedList<>();
        supplementaryList.add(b1);
        supplementaryList.add(b2);
        supplementaryList.add(b3);

        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);
        bookDao.add(b4);

        List<BookDTO> result = bookDao.findByCategory("category1");
        Comparator<BookDTO> comparator = Comparator.comparingLong(BookDTO::getIdBook);
        result.sort(comparator);


        assertTrue(result.equals(supplementaryList));

        bookDao.remove(b1);
        bookDao.remove(b2);
        bookDao.remove(b3);
        bookDao.remove(b4);
    }

    @Test
    void findByTitleTest() {
        BookJpaDAO bookDao = new BookHibernateDAO();
        BookDTO b1 = new BookDTO("title1", "author1", "category1", BookDTO.rentalTime.SEVENDAYS, 10);
        BookDTO b2 = new BookDTO("title1", "author2", "category2", BookDTO.rentalTime.ONEDAY, 2);
        BookDTO b3 = new BookDTO("title1", "author3", "category3", BookDTO.rentalTime.THREEMONTHS, 1);
        BookDTO b4 = new BookDTO("title4", "author4", "category4", BookDTO.rentalTime.SEVENDAYS, 0);

        List<BookDTO> supplementaryList = new LinkedList<>();
        supplementaryList.add(b1);
        supplementaryList.add(b2);
        supplementaryList.add(b3);

        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);
        bookDao.add(b4);

        List<BookDTO> result = bookDao.findByTitle("title1");
        Comparator<BookDTO> comparator = Comparator.comparingLong(BookDTO::getIdBook);
        result.sort(comparator);


        assertTrue(result.equals(supplementaryList));

        bookDao.remove(b1);
        bookDao.remove(b2);
        bookDao.remove(b3);
        bookDao.remove(b4);

    }


}