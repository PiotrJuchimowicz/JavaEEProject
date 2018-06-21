package com.company.project.JDBC;

import com.company.project.Models.BookDTO;

import java.util.List;

public interface JdbcInterface {
    List<BookDTO> authorBooksWithRegularExpression(String param);

    List<BookDTO> titleBookWithRegularExpression(String param);

    List<BookDTO> categoryBookWithRegularExpression(String param);
}
