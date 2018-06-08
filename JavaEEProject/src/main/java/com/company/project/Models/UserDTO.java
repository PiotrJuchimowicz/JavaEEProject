package com.company.project.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@NamedQueries({@NamedQuery(name = "findAllUsers", query = "Select u From UserDTO  u ")})
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<IssueDTO> issuesOfThisUser;


    public UserDTO() {
    }

    public UserDTO(String name, String surname, String email, int password, double payment, Role role, List<IssueDTO> issuesOfThisUser) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.payment = payment;
        this.role = role;
        this.issuesOfThisUser = issuesOfThisUser;
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

    // Id typu string jest potrzebne do uzyskania adresu url (żeby z widoku users-get-all przejść do widoku user-get-one)
    public String idUserToString() { return String.valueOf(idUser); }

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
}
