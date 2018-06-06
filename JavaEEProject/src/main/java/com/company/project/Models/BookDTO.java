package com.company.project.Models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "BOOK")
@NamedQueries({@NamedQuery(name = "findAllBooks", query = "select b from BookDTO  b "), @NamedQuery(name = "findByCategory", query = "select b From BookDTO  b where b.category=:category"), @NamedQuery(name = "findByAuthor", query = "select  b from  BookDTO b   where b.author=:author"), @NamedQuery(name = "findByTitle", query = "select b From BookDTO  b where b.title=:title")})
public class BookDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBook;

    public enum rentalTime {
        ONEDAY, SEVENDAYS, THREEMONTHS

    }

   //adnotacje tu będą jeszcze
    @NotNull
    @Size(min=1, message = "wpisz cos")
    private String title;
    private String author;
    private String category;
    @Enumerated(EnumType.STRING)
    private rentalTime rentalTime;
    private int numberOfCopies;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<IssueDTO> issuesOfThisBook;


    public BookDTO() { }

    public BookDTO(String title, String author, String category, BookDTO.rentalTime rentalTime, int numberOfCopies, List<IssueDTO> issuesOfThisBook) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rentalTime = rentalTime;
        this.numberOfCopies = numberOfCopies;
        this.issuesOfThisBook = issuesOfThisBook;
    }

    public List<IssueDTO> getIssuesOfThisBook() {
        return issuesOfThisBook;
    }

    public void setIssuesOfThisBook(List<IssueDTO> issuesOfThisBook) {
        this.issuesOfThisBook = issuesOfThisBook;
    }

    public long getIdBook() {
        return idBook;
    }

    // Id typu string jest potrzebne do uzyskania adresu url (żeby z widoku books-get-all przejść do widoku book-get-one)
    public String idBookToString() { return String.valueOf(idBook); }

    public void setIdBook(long id_book) {
        this.idBook = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BookDTO.rentalTime getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(BookDTO.rentalTime rentalTime) {
        this.rentalTime = rentalTime;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "idBook=" + idBook +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", rentalTime=" + rentalTime +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }


}
