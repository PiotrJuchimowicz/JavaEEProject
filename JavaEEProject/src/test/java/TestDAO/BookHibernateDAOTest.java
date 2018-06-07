package TestDAO;


import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import org.junit.jupiter.api.Test;

import javax.persistence.Table;
import java.awt.print.Book;
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
        List<IssueDTO> issuesOfThisBook = null;

        BookDTO book = new BookDTO(title, author, category, rentalTime, numberOfCopies, issuesOfThisBook);

        bookDao.add(book);
        long id1 = book.getIdBook();
        long id2 = bookDao.get(id1).getIdBook();

        assert id1 == id2;

        book.setAuthor("other Author");
        bookDao.update(book);

        assert book.getAuthor().equals("other Author");
        bookDao.remove(bookDao.get(id1));
        assert bookDao.get(id1)==null;
    }

    @Test
    void findAllBooks()
    {
        BookJpaDAO bookDao= new BookHibernateDAO();
        BookDTO b1,b2,b3;
        b1= new BookDTO("1","1","1",null,1,null);
        b2 =  new BookDTO("2","2","2",null,2,null);
        b3 = new BookDTO("3","3","3",null,3,null);

        bookDao.add(b1);
        bookDao.add(b2);
        bookDao.add(b3);

        List<BookDTO> result = bookDao.findAllBooks();
        for(BookDTO b : result)
            System.out.println(b);

        bookDao.remove(b1);
        bookDao.remove(b2);
        bookDao.remove(b3);

        //assert (result.get(0) );
    }

    @Test
    void findByAuthor()
    {

    }

    @Test
    void findByCategory()
    {

    }

    @Test
    void findByTitle()
    {

    }




}