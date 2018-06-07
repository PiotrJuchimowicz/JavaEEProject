package TestDAO;

import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

//TODO
class UserHibernateDAOTest {


    @Test
    void CRUDTest() {
        UserDTO userDTO;
        UserJpaDAO userDao = new UserHibernateDAO();
        String name = "anyName";
        String surname = "anySurname";
        String email = "anyEmail";
        int password = 1234;
        double payment = 1000;
        UserDTO.Role role = UserDTO.Role.ADMIN;
        userDTO = new UserDTO(name, surname, email, password, payment, role);

        //czy dziala dodawanie i pobieranie go z powrtoem
        userDao.add(userDTO);

        long id = userDTO.getIdUser();

        UserDTO afterUser = userDao.get(id);

        assertTrue(afterUser.equals(userDTO));

        //sprawdzanie update
        userDTO.setEmail("NewEmail");
        userDao.update(userDTO);

        assertTrue(userDao.get(id).getEmail().equals("NewEmail"));

        userDao.remove(userDTO);
        //sprawdzanie usuwania
        assertTrue(userDao.get(id) == null);

    }

    //Sprawdza czy taki  klient wypożyczył taką książkę
    @Test
    void didHeBorrowThatBookTest() {
        //tworze usera
        UserDTO user;
        UserJpaDAO userDao = new UserHibernateDAO();
        String name = "anyName";
        String surname = "anySurname";
        String email = "anyEmail";
        int password = 1234;
        double payment = 1000;
        UserDTO.Role role = UserDTO.Role.ADMIN;

        user = new UserDTO(name, surname, email, password, payment, role);

        //tworze ksiazke
        BookJpaDAO bookDao = new BookHibernateDAO();
        String title = "any Title";
        String author = "any Author";
        String category = "any Category";
        BookDTO.rentalTime rentalTime = BookDTO.rentalTime.SEVENDAYS;
        int numberOfCopies = 20;

        BookDTO book = new BookDTO(title, author, category, rentalTime, numberOfCopies);

        //dodaje ich
        userDao.add(user);
        bookDao.add(book);
        long userId = user.getIdUser();
        long bookId = book.getIdBook();

        //tworze zamowienie i dodaje je
        IssueDTO issueDTO = new IssueDTO(book, user, null, null, null);
        IssueJpaDAO issueDao = new IssueHibernateDAO();
        issueDao.add(issueDTO);
       




        //sprzatanie
        issueDao.remove(issueDTO);
        userDao.remove(user);
        bookDao.remove(book);



    }

    @Test
    void findAllUsersTest() {
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
       UserDTO u1, u2, u3;
        u1 = new UserDTO("1","2","3",1,1, UserDTO.Role.ADMIN);
        u2 = new UserDTO("1","2","3",1,1, UserDTO.Role.ADMIN);
        u3 =new UserDTO("1","2","3",1,1, UserDTO.Role.ADMIN);

        //pomocnicza lista
        List<UserDTO> supplementaryList = new LinkedList<>();
        supplementaryList.add(u1);
        supplementaryList.add(u2);
        supplementaryList.add(u3);


        //dodaje userow
        userJpaDAO.add(u1);
        userJpaDAO.add(u2);
        userJpaDAO.add(u3);

        //bierze userow z bazy
        List<UserDTO> result =userJpaDAO.findAllUsers();
        //jesli listy sa rowne to  musza miec rowniez elementy w tej samej kolejnosci
        //sortowanko :
        Comparator<UserDTO> comparator = Comparator.comparingLong(UserDTO::getIdUser);
        result.sort(comparator);


        //assercja
        assertTrue(result.equals(supplementaryList));


        //sprzatamy
        userJpaDAO.remove(u1);
        userJpaDAO.remove(u2);
        userJpaDAO.remove(u3);

    }

}