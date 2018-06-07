package com.company.project.Models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.LinkedList;
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

    //domyslnie pusta lista powiazana z dana ksiazka(do ktorej potem bedzie mozna dodawac)
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<IssueDTO> issuesOfThisBook  = new LinkedList<>();


    public BookDTO() { }

    public BookDTO(String title, String author, String category, BookDTO.rentalTime rentalTime, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rentalTime = rentalTime;
        this.numberOfCopies = numberOfCopies;

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

    //Nadpisana metoda equals do porownywania dwoch ksiazek
    @Override
    public boolean equals(Object o) {
        //o moze byc null
        if (o == null)
            return false;
        //o moze byc tym samym obiektem co this
        else if (o == this)
            return true;

        //sprawdzanie czy to ta sama klasa
        if (!(o instanceof BookDTO))
            return false;

        //rzutowanie
        BookDTO bookDTO = (BookDTO) o;

        //sprawdzanie pól
        if (!(this.author.equals(bookDTO.author)))
            return false;
        if (!(this.category.equals(bookDTO.category)))
            return false;
        if (!(this.idBook == bookDTO.idBook))
            return false;
        if (!(this.numberOfCopies == bookDTO.numberOfCopies))
            return false;
        if (!(this.rentalTime == bookDTO.rentalTime))
            return false;
        if (!(this.title.equals(bookDTO.title)))
            return false;

        //zatem ksiazki maja listy swoich zamowien :


        //jesli obie maja listy sprawdzamy czy sa rowne(musza miec taka sama dlugosc i
        //miec ksiazki te same w tej samej kolejnosci)


        List<IssueDTO> l1 = new LinkedList<>(this.issuesOfThisBook);
        List<IssueDTO> l2 = new LinkedList<>(bookDTO.issuesOfThisBook);
        Comparator<IssueDTO> comparator = Comparator.comparingLong(IssueDTO::getIdIssue);

        l1.sort(comparator);
        l2.sort(comparator);

        return l1.size() == l2.size() && l1.equals(l2);


    }




}
