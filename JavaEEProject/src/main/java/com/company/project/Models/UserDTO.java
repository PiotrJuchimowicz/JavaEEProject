package com.company.project.Models;

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

    public enum Role {
        CLIENT, ADMIN, EMPLOYEE


    }

    private String name, surname, email;
    private int password;//hasz hasła
    private double payment;
    @Enumerated(EnumType.STRING)
    private Role role;

    //domyslnie pusta lista powiazana z danym userem do ktorej potem bedzie mozna dodawac
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<IssueDTO> issuesOfThisUser = new LinkedList<>();


    public UserDTO() {
    }

    public UserDTO(String name, String surname, String email, int password, double payment, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.payment = payment;
        this.role = role;
    }

    //Dodaje do aktualnej listy zamowien nowe zamowienie
    public void addIssue(IssueDTO issueDTO)
    {
        issuesOfThisUser.add(issueDTO);
    }

    public List<IssueDTO> getIssuesOfThisUser() {
        return issuesOfThisUser;
    }

    public void setIssuesOfThisUser(List<IssueDTO> issuesOfThisUser) {
        this.issuesOfThisUser = issuesOfThisUser;
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", payment=" + payment +
                ", role=" + role +
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
        if (!(this.password == userDTO.password))
            return false;
        if (!(Double.compare(this.payment,userDTO.payment)==0 ))
            return false;
        if (!(this.surname.equals(userDTO.surname)))
            return false;
        if (!(this.role.equals(userDTO.role)))
            return false;






        List<IssueDTO> l1 = new LinkedList<>(this.issuesOfThisUser);
        List<IssueDTO> l2 = new LinkedList<>(userDTO.issuesOfThisUser);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);

        l1.sort(comparator);
        l2.sort(comparator);

        return l1.size() == l2.size() && l1.equals(l2);


    }
}
