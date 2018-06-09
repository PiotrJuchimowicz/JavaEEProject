package com.company.project.Models;

import com.company.project.HibernateDAO.IssueHibernateDAO;
import com.company.project.JpaDAO.IssueJpaDAO;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "USER")
@NamedQueries({@NamedQuery(name = "findAllUsers", query = "Select u From UserDTO  u "),@NamedQuery(name="didHeBorrowThatBook",query = "Select i From IssueDTO  i Where i.user.id=:userId AND i.book.id=:bookId")})
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;



    @OneToOne(mappedBy = "username")
    private authorities authority;



    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private int enabled;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;//hasz hasła
    @Column(nullable = false)
    private double payment;
    @Enumerated(EnumType.STRING)


    //domyslnie pusta lista powiazana z danym userem do ktorej potem bedzie mozna dodawac
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<IssueDTO> issuesOfThisUser = new LinkedList<>();


    public UserDTO() {
    }

    public UserDTO(authorities authority, String username, int enabled, String name, String surname, String email, String password, double payment) {
        this.authority = authority;
        this.username = username;
        this.enabled = enabled;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.payment = payment;
    }

    //Dodaje do aktualnej listy zamowien nowe zamowienie
    public void addIssue(IssueDTO issueDTO)
    {
        issuesOfThisUser.add(issueDTO);
    }
    public void removeIssue(IssueDTO issueDTO)
    {
        issuesOfThisUser.remove(issueDTO);
    }

    public List<IssueDTO> getIssuesOfThisUser() {
        return issuesOfThisUser;
    }

    public void setIssuesOfThisUser(List<IssueDTO> issuesOfThisUser) {
        this.issuesOfThisUser = issuesOfThisUser;
    }


    public authorities getAuthority() {
        return authority;
    }

    public void setAuthority(authorities authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long userId) {
        this.idUser = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "idUser=" + idUser +
                ", authority=" + authority +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", payment=" + payment +
                '}';
    }

    //Nadpisana metoda equals do porownywania dwoch uzytkownikow
    @Override
    public boolean equals(Object o) {
        //o moze byc null
        if (o == null)
            return false;
            //o moze byc tym samym obiektem co this
        else if (o == this)
            return true;

        //sprawdzanie czy to ta sama klasa
        if (!(o instanceof UserDTO))
            return false;

        //rzutowanie
       UserDTO userDTO = (UserDTO) o;

        //sprawdzanie pól
        if (!(this.email.equals(userDTO.email)))
            return false;
        if (!(this.name.equals(userDTO.name)))
            return false;
        if (!(this.idUser == userDTO.idUser))
            return false;
        if (!(this.password.equals(userDTO.password) ))
            return false;
        if (!(Double.compare(this.payment,userDTO.payment)==0 ))
            return false;
        if (!(this.surname.equals(userDTO.surname)))
            return false;
        if(this.getAuthority()!=null && userDTO.getAuthority()!=null) {
            if (!(this.getAuthority().equals(userDTO.getAuthority())))
                return false;
        }
        if(!(this.username.equals(userDTO.username)))
            return false;







        if(this.getIssuesOfThisUser()==null || userDTO.getIssuesOfThisUser()==null)
            return true;

        IssueJpaDAO issueJpaDAO = new IssueHibernateDAO();
        List<IssueDTO> l1 = new LinkedList<>(issueJpaDAO.findIssuesByThisUser(this.getIdUser()));
        List<IssueDTO> l2 = new LinkedList<>(issueJpaDAO.findIssuesByThisUser(userDTO.getIdUser()));

        if(!(l1.size()==l2.size()))
                return false;
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);
        l1.sort(comparator);
        l2.sort(comparator);

        return l1.equals(l2);







    }
}
