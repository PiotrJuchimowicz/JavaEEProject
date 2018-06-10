package TestDAO;

import com.company.project.HibernateDAO.BookHibernateDAO;
import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.BookJpaDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.AuthoritiesDTO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

//TODO
 public class UserHibernateDAOTest {


    @Test
    void CRUDTest() {
        UserDTO userDTO;
        UserJpaDAO userDao = new UserHibernateDAO();
        String name = "anyName";
        String surname = "anySurname";
        String email = "anyEmail";
        String password = "1234";
        String username = "usernameeeeee";
        double payment = 1000;

        userDTO = new UserDTO(username,1,name, surname, email, password, payment );

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


    @Test
    void CRUDTestForIssue() {
        BookDTO bookDTO = new BookDTO("title", "author", "category", BookDTO.rentalTime.SEVENDAYS, 4);
        UserDTO userDTO = new UserDTO("username",1,"Name", "Surname", "email", "1", 1000.21 );

        BookJpaDAO bookJpaDAO = new BookHibernateDAO();
        UserJpaDAO userJpaDAO = new UserHibernateDAO();

        bookJpaDAO.add(bookDTO);
        userJpaDAO.add(userDTO);

        IssueDTO issueDTO;
        LocalDateTime reservationDate = LocalDateTime.of(2018, Month.APRIL, 2, 1, 1);// data rezerwacji
        LocalDateTime issueDate = LocalDateTime.of(2018, Month.AUGUST, 2, 1, 1);// data wydania egzemplarza
        LocalDateTime returnDate = LocalDateTime.now();//data zwrotu egzemplarza
        issueDTO = new IssueDTO(bookDTO, userDTO, reservationDate, issueDate, returnDate);

        //uzupelniam starym wersjom book oraz ksiazki dane zamowienie
        bookDTO.addIssue(issueDTO);
        userDTO.addIssue(issueDTO);

        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();


        //dodaje
        issueJpaDAO.add(issueDTO);

        //test dla add i get

        long idIssue = issueDTO.getIdIssue();
        long idBook = bookDTO.getIdBook();
        long idUser = userDTO.getIdUser();
        //biore
        IssueDTO afterIssue = issueJpaDAO.get(idIssue);

        //porownuje
        assertTrue(afterIssue.equals(issueDTO));


        //testy dla update
        LocalDateTime date = LocalDateTime.now();
        issueDTO.setIssueDate(date);
        issueJpaDAO.update(issueDTO);

        assertTrue(issueJpaDAO.get(idIssue).getIssueDate().withNano(0).withSecond(0).equals(date.withSecond(0).withNano(0)));



        //JESLI CHCE USUWAC ISSUE JAKO PIERWSZE MUSZA BYC TE 2 LINIJKI PO USUNIECIU  ISSUE !!!!!!!!
        issueJpaDAO.remove(issueDTO);
        userDTO.removeIssue(issueDTO);
        bookDTO.removeIssue(issueDTO);
        userJpaDAO.remove(userDTO);
        bookJpaDAO.remove(bookDTO);




        //userJpaDAO.update(userDTO);
        //bookJpaDAO.update(bookDTO);

        //sprzatam


        //sprawdzam czy usunalem
        assertTrue(issueJpaDAO.get(idIssue) == null);
        assertTrue(bookJpaDAO.get(idBook) == null);
        assertTrue(userJpaDAO.get(idUser) == null);


    }

    // TEST DLA :Sprawdza czy taki  klient wypożyczył taką książkę
    @Test
    void didHeBorrowThatBookTest() {
        //tworze usera
        UserDTO user;
        UserJpaDAO userDao = new UserHibernateDAO();
        String name = "anyName";
        String surname = "anySurname";
        String email = "anyEmail";
        String password = "1234";
        double payment = 1000;
        AuthoritiesDTO role=null;

        user = new UserDTO("username",1,name, surname, email, password, payment);

        //tworze ksiazke
        BookJpaDAO bookDao = new BookHibernateDAO();
        String title = "any Title";
        String author = "any Author";
        String category = "any Category";
        BookDTO.rentalTime rentalTime = BookDTO.rentalTime.SEVENDAYS;
        int numberOfCopies = 20;

        BookDTO book = new BookDTO(title, author, category, rentalTime, numberOfCopies);

        //przed dodaniem zamowienia na ta ksiazke dla tego usera musze ICH na poczatku dodac- inaczej nie zadziala
        //dodaje ich
        userDao.add(user);
        bookDao.add(book);
        long userId = user.getIdUser();
        long bookId = book.getIdBook();

        //tworze zamowienie i dodaje je
        IssueDTO issueDTO = new IssueDTO(book, user, null, null, null);
        IssueJpaDAO issueDao = new IssueHibernateDAO();
        issueDao.add(issueDTO);//dodaje issue oraz automatycznie userowi oraz ksiazce
        // dodawane jest do listy to issue


        //sprzatanie
        issueDao.remove(issueDTO);
        userDao.remove(user);
        bookDao.remove(book);


    }

    @Test
    void findAllUsersTest() {
        UserJpaDAO userJpaDAO = new UserHibernateDAO();
        UserDTO u1, u2, u3;
        u1 = new UserDTO("username1",1,"name", "surname", "email", "password", 1);
        u2 = new UserDTO("username2",1,"name", "surname", "email", "password", 1);
        u3 = new UserDTO("username3",1,"name", "surname", "email", "password", 1);

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
        List<UserDTO> result = userJpaDAO.findAllUsers();
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