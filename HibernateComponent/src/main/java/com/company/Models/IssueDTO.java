package com.company.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ISSUE")
@NamedQueries({@NamedQuery(name = "findIssuesOfThisBook", query = "select  i from IssueDTO  i Where i.book.id=:id AND i.issueDate is not null"), @NamedQuery(name = "findReservationsOfThisBook", query = "select  i From IssueDTO  i where i.book.id=:id and (i.reservationDate is not null and i.issueDate is null)"), @NamedQuery(name = "findIssuesByThisUser", query = "select i FROM  IssueDTO i where i.user.id=:id and (i.issueDate is not null) "), @NamedQuery(name = "findReservationsByThisUser", query = "select i from IssueDTO  i where  i.user.id=:id and (i.issueDate is  null and i.reservationDate is not null)"), @NamedQuery(name = "findAllReservations", query = "select i from IssueDTO i where i.reservationDate is not null and i.issueDate is null"), @NamedQuery(name = "findAllIssues", query = "select i from  IssueDTO  i where i.issueDate is not null")})
public class IssueDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIssue;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBook")
    private BookDTO book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private UserDTO user;

    //czas w formacie yyyy-MM-dd-HH-mm-ss.
    private LocalDateTime reservationDate;// data rezerwacji
    private LocalDateTime issueDate;// data wydania egzemplarza
    private LocalDateTime returnDate;//data zwrotu egzemplarza


    public IssueDTO() {
    }

    public IssueDTO(BookDTO book, UserDTO user, LocalDateTime reservationDate, LocalDateTime issueDate, LocalDateTime returnDate) {
        this.book = book;
        this.user = user;
        this.reservationDate = reservationDate;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public long getIdReservation() {
        return idIssue;
    }

    public void setIdReservation(long idIssue) {
        this.idIssue = idIssue;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "idIssue=" + idIssue +
                ", reservationDate=" + reservationDate +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
