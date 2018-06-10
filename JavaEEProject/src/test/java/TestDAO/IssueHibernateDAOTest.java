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
import org.apache.catalina.User;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO
public class IssueHibernateDAOTest {
    private static BookDTO b1, b2, b3;
    private static UserDTO u1, u2, u3;
    private static IssueDTO i1, i2, i3, i4, i5, i6;
    private static BookJpaDAO bookJpaDAO;
    private static UserJpaDAO userJpaDAO;
    private static IssueJpaDAO issueJpaDAO;
    private static List<IssueDTO> listOfAllIssues = new LinkedList<>();
    private static List<IssueDTO> listOfAllReservations = new LinkedList<>();
    private static List<IssueDTO> listOfIssuesByU1 = new LinkedList<>();
    private static List<IssueDTO> listOfReservationsByU1 = new LinkedList<>();
    private static List<IssueDTO> listOfIssuesByB3 = new LinkedList<>();
    private static List<IssueDTO> listOfReservationsByB1 = new LinkedList<>();

    @BeforeAll
    static void init() {
        b1 = new BookDTO("1b", "1b", "1b", BookDTO.rentalTime.SEVENDAYS, 1);
        b2 = new BookDTO("2b", "2b", "2b", BookDTO.rentalTime.SEVENDAYS, 2);
        b3 = new BookDTO("3b", "3b", "3b", BookDTO.rentalTime.ONEDAY, 3);
        bookJpaDAO = new BookHibernateDAO();
        bookJpaDAO.add(b1);
        bookJpaDAO.add(b2);
        bookJpaDAO.add(b3);

        u1 = new UserDTO("1u", 1, "1u", "1u", "1u", "p", 1);
        u2 = new UserDTO( "2u", 1, "2u", "2u", "2u", "p", 2);
        u3 = new UserDTO( "3u", 1, "3u", "3u", "3u", "p", 31);
        userJpaDAO = new UserHibernateDAO();
        userJpaDAO.add(u1);
        userJpaDAO.add(u2);
        userJpaDAO.add(u3);

        //reservations
        i1 = new IssueDTO(b1, u1, LocalDateTime.now(), null, null);
        i2 = new IssueDTO(b1, u2, LocalDateTime.now(), null, null);


        //issues without reservations
        i3 = new IssueDTO(b3, u3, null, LocalDateTime.now(), null);
        i6 = new IssueDTO(b1, u1, null, LocalDateTime.now(), null);

        //issues with reservation
        i4 = new IssueDTO(b3, u2, LocalDateTime.now(), LocalDateTime.now(), null);
        i5 = new IssueDTO(b3, u1, LocalDateTime.now(), LocalDateTime.now(), null);

        //Uzupelnianie testowych list

        listOfAllIssues.add(i3);
        listOfAllIssues.add(i4);
        listOfAllIssues.add(i5);
        listOfAllIssues.add(i6);

        listOfAllReservations.add(i1);
        listOfAllReservations.add(i2);

        listOfIssuesByB3.add(i3);
        listOfIssuesByB3.add(i4);
        listOfIssuesByB3.add(i5);

        listOfReservationsByB1.add(i1);
        listOfReservationsByB1.add(i2);

        listOfIssuesByU1.add(i6);
        listOfIssuesByU1.add(i5);

        listOfReservationsByU1.add(i1);

        issueJpaDAO = new IssueHibernateDAO();

        issueJpaDAO.add(i1);
        b1.addIssue(i1);
        u1.addIssue(i1);

        issueJpaDAO.add(i2);
        b1.addIssue(i2);
        u2.addIssue(i2);

        issueJpaDAO.add(i3);
        b3.addIssue(i3);
        u3.addIssue(i3);

        issueJpaDAO.add(i4);
        b3.addIssue(i4);
        u2.addIssue(i4);

        issueJpaDAO.add(i5);
        b3.addIssue(i5);
        u1.addIssue(i5);

        issueJpaDAO.add(i6);
        b1.addIssue(i6);
        u1.addIssue(i6);


    }


    @AfterAll
    static void clean() {
        bookJpaDAO = new BookHibernateDAO();
        bookJpaDAO.remove(b1);
        bookJpaDAO.remove(b2);
        bookJpaDAO.remove(b3);

        userJpaDAO = new UserHibernateDAO();
        userJpaDAO.remove(u1);
        userJpaDAO.remove(u2);
        userJpaDAO.remove(u3);

        issueJpaDAO = new IssueHibernateDAO();

        issueJpaDAO.remove(i1);
        b1.removeIssue(i1);
        u1.removeIssue(i1);

        issueJpaDAO.remove(i2);
        b1.removeIssue(i2);
        u2.removeIssue(i2);

        issueJpaDAO.remove(i3);
        b3.removeIssue(i3);
        u3.removeIssue(i3);

        issueJpaDAO.remove(i4);
        b3.removeIssue(i4);
        u2.removeIssue(i4);

        issueJpaDAO.remove(i5);
        b3.removeIssue(i5);
        u1.removeIssue(i5);

        issueJpaDAO.remove(i6);
        b1.removeIssue(i6);
        u1.removeIssue(i6);


    }


    //Znajduje wypożyczenia książki o danym id
    @Test
    void findIssuesOfThisBook() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        long id = b3.getIdBook();
        List<IssueDTO> result = issueJpaDAO.findIssuesOfThisBook(id);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfIssuesByB3.sort(comparator);

        assertTrue(result.equals(listOfIssuesByB3));


    }

    @Test
    void findIssuesByThisUser() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        long id = u1.getIdUser();
        List<IssueDTO> result = issueJpaDAO.findIssuesByThisUser(id);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfIssuesByU1.sort(comparator);

        assertTrue(result.equals(listOfIssuesByU1));

    }


    @Test
    void findAllIssues() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        List<IssueDTO> result = issueJpaDAO.findAllIssues();
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfAllIssues.sort(comparator);

        assertTrue(result.equals(listOfAllIssues));

    }


    //Znajduje rezerwacje i tylko rezerwacje(jeśli książka została wypożyczona to nie będzie uwzględniona w wynikach) danej książki
    @Test
    void findReservationsOfThisBook() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        long id = b1.getIdBook();
        List<IssueDTO> result = issueJpaDAO.findReservationsOfThisBook(id);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfReservationsByB1.sort(comparator);

        assertTrue(result.equals(listOfReservationsByB1));

    }

    @Test
    void findReservationsByThisUser() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        long id = u1.getIdUser();
        List<IssueDTO> result = issueJpaDAO.findReservationsByThisUser(id);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfReservationsByU1.sort(comparator);

        assertTrue(result.equals(listOfReservationsByU1));

    }

    @Test
    void findAllReservations() {
        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        List<IssueDTO> result = issueJpaDAO.findAllReservations();
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        result.sort(comparator);
        listOfAllReservations.sort(comparator);

        assertTrue(result.equals(listOfAllReservations));

    }
}