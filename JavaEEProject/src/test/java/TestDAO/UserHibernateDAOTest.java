package TestDAO;

import com.company.project.HibernateDAO.UserHibernateDAO;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO
class UserHibernateDAOTest {


    @Test
    void CRUDTest() {
        UserDTO userDTO;
        UserJpaDAO userDao = new UserHibernateDAO();
        String name = "anyName";
        String surname="anySurname";
        String email="anyEmail";
        int password=1234;
        double payment=1000;
        UserDTO.Role role= UserDTO.Role.ADMIN;
        userDTO= new

    }

    //Sprawdza czy taki  klient wypożyczył taką książkę
    @Test
    boolean didHeBorrowThatBook(UserDTO userDTO, BookDTO bookDTO) {

    }

    @Test
    List<UserDTO> findAllUsers() {

    }

}