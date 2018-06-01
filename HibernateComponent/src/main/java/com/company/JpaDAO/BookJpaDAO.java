package com.company.JpaDAO;

import com.company.Models.BookDTO;

import java.util.List;

public interface BookJpaDAO {

    void add(BookDTO b);

    BookDTO get(long id);

    void update(BookDTO b);

    void remove(BookDTO b);


    List<BookDTO> findAllBooks();

    List<BookDTO> findByCategory(String category);

    List<BookDTO> findByAuthor(String author);

    List<BookDTO> findByTitle(String title);


}
