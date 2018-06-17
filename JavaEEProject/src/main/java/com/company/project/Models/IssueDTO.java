package com.company.project.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ISSUE")
@NamedQueries({@NamedQuery(name = "findIssuesOfThisBook", query = "select  i from IssueDTO  i Where i.book.id=:id AND i.issueDate is not null"), @NamedQuery(name = "findReservationsOfThisBook", query = "select  i From IssueDTO  i where i.book.id=:id and (i.reservationDate is not null and i.issueDate is null)"), @NamedQuery(name = "findIssuesByThisUser", query = "select i FROM  IssueDTO i where i.user.id=:id and (i.issueDate is not null) "), @NamedQuery(name = "findReservationsByThisUser", query = "select i from IssueDTO  i where  i.user.id=:id and (i.issueDate is  null and i.reservationDate is not null)"), @NamedQuery(name = "findAllReservations", query = "select i from IssueDTO i where i.reservationDate is not null and i.issueDate is null"), @NamedQuery(name = "findAllIssues", query = "select i from  IssueDTO  i where i.issueDate is not null"), @NamedQuery(name="findReservationById", query = "select i from IssueDTO i where i.issueDate is null and i.reservationDate is not null and i.id=:id") , @NamedQuery(name = "findNotReturned", query = "select i from IssueDTO i where i.returnDate is null and i.issueDate is not null") , @NamedQuery(name = "findNotReturnedOfUser" , query = "select i from IssueDTO i where i.returnDate is null and i.issueDate is not null and i.user.id = :id")})
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
    @Column(nullable = true)
    private LocalDateTime reservationDate;// data rezerwacji
    @Column(nullable = true)
    private LocalDateTime issueDate;// data wydania egzemplarza
    @Column(nullable = true)
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

    public long getIdIssue() {
        return idIssue;
    }

    public String getIdIssueToString(){
        return String.valueOf(idIssue);
    }

    public void setIdIssue(long idIssue) {
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
                ", book=" + book +
                ", user=" + user +
                ", reservationDate=" + reservationDate +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        //o moze byc null
        if (o == null)
            return false;
            //o moze byc tym samym obiektem co this
        else if (o == this)
            return true;

        //sprawdzanie czy to ta sama klasa
        if (!(o instanceof IssueDTO))
            return false;

        //rzutowanie
        IssueDTO issueDTO = (IssueDTO) o;

        if(!(this.getBook().getIdBook()==issueDTO.getBook().getIdBook()))
            return false;

        if(!(this.getUser().getIdUser()==issueDTO.getUser().getIdUser()))
            return false;



        if(!(this.idIssue==issueDTO.idIssue))
            return false;

        //POROWNUJEMY DATY Z DOKLADNOSCIA DO MINUTY
        if(this.getIssueDate()!=null && issueDTO.getIssueDate()!=null) {
            if (!(this.issueDate.withSecond(0).withNano(0).equals(issueDTO.issueDate.withSecond(0).withNano(0))))
                return false;
        }

        if(this.getReservationDate()!=null && issueDTO.getReservationDate()!=null) {
            if (!(this.reservationDate.withSecond(0).withNano(0).equals(issueDTO.reservationDate.withSecond(0).withNano(0))))
                return false;
        }

        if(this.getReturnDate()!=null && issueDTO.getReturnDate()!=null) {
            if (!(this.returnDate.withSecond(0).withNano(0).equals(issueDTO.returnDate.withSecond(0).withNano(0))))
                return false;
        }


        return  true;
    }
}
