package com.company.project.Controllers;

import com.company.project.HibernateDAO.AuthoritiesHibernateDao;
import com.company.project.JpaDAO.AuthoritiesJpaDao;
import com.company.project.JpaDAO.UserJpaDAO;
import com.company.project.Models.AuthoritiesDTO;
import com.company.project.Models.BookDTO;
import com.company.project.Models.IssueDTO;
import com.company.project.Models.UserDTO;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {


    private static final Logger logger = LogManager.getLogger(UsersController.class);


    @Autowired
    private UserJpaDAO userJpaDAO;

    @Autowired
    private AuthoritiesJpaDao authoritiesJpaDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping("/removeconfirm/{id}") //Z: widok pośredni między szczegółami użytkownika a usunięciem
    public String removeConfirm(@PathVariable long id, Model theModel) {
        UserDTO user = userJpaDAO.get(id);
        theModel.addAttribute("user", user);
        logger.info("message4");
        return "user-remove-confirm";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {

        userJpaDAO.remove(userJpaDAO.get(id));
        logger.info("message4");
        return "redirect:/users/findall";
    }

    @RequestMapping("/payment")
    public String resetPayment(@RequestParam("id") Long idUser){

        UserDTO user = userJpaDAO.get(idUser);
        user.setPayment(0);
        userJpaDAO.update(user);
        logger.info("message4");

        return "redirect:/users/findall";
    }

    // tu trzeba zmienić na AuthoritiesDTO
    //Z: Służy do stworzenia listy do wybierania opcji
    @ModelAttribute("roles")
    public AuthoritiesDTO.Role[] roles() {
        return AuthoritiesDTO.Role.values();
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadAddPage(Model model) {
        model.addAttribute("user", new UserDTO());
        logger.info("message4");
        return "users-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitAdd(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model theModel) {
        logger.info("message4");

        if (user.getUsername() == null || user.getName() == null || user.getSurname() == null || user.getEmail() == null) {
            theModel.addAttribute("error", "Wszystkie pola muszą być wypełnione");
            return "users-add";
        } else {


            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(user.getUsername() + "/']" + user.getSurname().substring(0, 2) + "@");

            System.out.println(user.getPassword());

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            theModel.addAttribute("confirm", "Dodano użytkownika");

            userJpaDAO.add(user);


            return "users-add";
        }
    }



    @RequestMapping("/findbyid/{id}")
    public String getById(@PathVariable long id, Model theModel) {
        UserDTO user = userJpaDAO.get(id);
        theModel.addAttribute("user", user);
        logger.info("message4");
        return "user-get-one";
    }

    @RequestMapping("/findall")
    public String getAll(Model theModel) {
        List<UserDTO> list = userJpaDAO.findAllUsers();
        theModel.addAttribute("users", list);

        return "users-get-all";
    }


    @RequestMapping("/userspanel")
    public String usersPanel(Model theModel) {

        long id = LoginController.getUserId(); //pobiera id z aktualnej sesji
        UserDTO user = userJpaDAO.get(id);

        theModel.addAttribute("user", user);

        logger.info("message4");

        return "users-panel";
    }

    @RequestMapping(value = "/userspanel/edit", method = RequestMethod.GET)
    public String editData(Model theModel) {

        long id = LoginController.getUserId();
        UserDTO userDTO = userJpaDAO.get(id);
        theModel.addAttribute("user", userDTO);

        logger.info("message4");

        return "users-edit";
    }

    @RequestMapping(value = "/userspanel/edit", method = RequestMethod.POST)
    public String editDateConfirm(@Valid @ModelAttribute("user") UserDTO userDTO, Model theModel) {
        long id = LoginController.getUserId();

        if(userDTO.getSurname()==null || userDTO.getEmail() == null || userDTO.getName() == null){
            theModel.addAttribute("error", "update");
            return "users-edit";
        }else {
            userJpaDAO.updateUser(id, userDTO.getEmail(), userDTO.getSurname(), userDTO.getName());
            theModel.addAttribute("confirm", "update");
            return "users-edit";
        }
    }


    @Scheduled(cron = "0 0 0 * * 1-7") //o północy każdego dnia tygodnia
    public void setPaymant() {

        List<UserDTO> users = userJpaDAO.findAllUsers();
        logger.info("message4");
        for (UserDTO user : users) {
            List<IssueDTO> issues = user.getIssuesOfThisUser();
            for (IssueDTO issueDTO : issues) {
                long days = rentalTime(issueDTO.getBook().getRentalTime()); //możliwy czas wypożyczenia


                //jesli obecna data jest po data wypożyczenia + czas na jaki moge wypożyczyć
                //to znaczy że przekroczyłam czas
                if (LocalDateTime.now().isAfter(issueDTO.getIssueDate().plusDays(days))) {
                    long daysRedundant =  daysBetweenDates(issueDTO.getIssueDate().plusDays(days),LocalDateTime.now());
                    //trzeba policzyć nadmiarowe dni
                    user.setPayment(user.getPayment() + daysRedundant);
                }

            }
        }
    }


    public int rentalTime(BookDTO.rentalTime rentalTime) {


        if (rentalTime.equals(BookDTO.rentalTime.SEVENDAYS))
            return 7;
        else if (rentalTime.equals(BookDTO.rentalTime.ONEDAY))
            return 1;
        else return 90; //THREEMONTHS
    }


    public  long daysBetweenDates(LocalDateTime date1, LocalDateTime date2) {


        long days1 = date1.getDayOfMonth();


        long days2 = date2.getDayOfMonth();


        if (date1.getYear() == date2.getYear()) {
            if (date1.getMonth().equals(date2.getMonth())) {
                return days2 - days1;
            } else {
                Month m1 = date1.getMonth().minus(1);
                Month m2 = date2.getMonth().minus(1);

                long daysOfMonth1 = 0;
                long daysOfMonth2 = 0;
                for (int i = 0; i < date1.getMonthValue(); i++) {
                    daysOfMonth1 = daysOfMonth1 + m1.length(false);
                    m1 = m1.minus(1);
                }
                for (int i = 0; i < date2.getMonthValue(); i++) {
                    daysOfMonth2 = daysOfMonth2 + m1.length(false);
                    m2 = m2.minus(1);
                }

                return (daysOfMonth2 + days2) - (daysOfMonth1 + days1);


            }
        } else {
            long y1 = date1.getYear();
            Month m1 = date1.getMonth().minus(1);
            long d1 = date1.getDayOfMonth();


            long y2 = date2.getYear();
            Month m2 = date2.getMonth().minus(1);
            long d2 = date2.getDayOfMonth();

            for (int i = 0; i < date1.getMonthValue(); i++) {
               d1 = d1 + m1.length(false);
                m1 = m1.minus(1);
            }

            for (int i = 0; i < date2.getMonthValue(); i++) {
                d2 = d2 + m2.length(false);
                m2 = m2.minus(1);
            }

            d1=d1 + y1 * 365;

            d2 = d2 + y2 * 365;

            return d2-d1;




        }

    }


}
