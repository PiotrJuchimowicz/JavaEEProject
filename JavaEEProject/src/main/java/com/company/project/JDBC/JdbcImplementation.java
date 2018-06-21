package com.company.project.JDBC;

import com.company.project.Models.BookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//Metody zwracaja null jesli nie ma czego zwrocic badz cos poszlo nie tak
public class JdbcImplementation implements JdbcInterface {


    @Override
    public List<BookDTO> authorBooksWithRegularExpression(String param) {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            System.out.println("Unable to connect with DB");
            return null;
        }


        String sql = " SELECT * FROM book b  WHERE b.author LIKE ? ";

        PreparedStatement pr = null;
        try {
            pr = connection.prepareStatement(sql);
        } catch (SQLException e) {

            System.out.println("Unable to create statement");
            return null;
        }

        try {
            pr.setString(1, '%' + param + '%');
        } catch (SQLException e) {
            System.out.println("Problem with parameter");
            return null;
        }

        ResultSet rs = null;
        try {
            rs = pr.executeQuery();
        } catch (SQLException e) {

            System.out.println("Unable to execute query.");
            return null;
        }

        List<BookDTO> result = new LinkedList<>();
        if (!resultMethod(rs, result))
            return null;


        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Problem with closing querry");
            return null;
        }

        return result;
    }

    @Override
    public List<BookDTO> titleBookWithRegularExpression(String param) {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            System.out.println("Unable to connect with DB");
            return null;
        }


        String sql = " SELECT * FROM book b  WHERE b.title LIKE ? ";

        PreparedStatement pr = null;
        try {
            pr = connection.prepareStatement(sql);
        } catch (SQLException e) {

            System.out.println("Unable to create statement");
            return null;
        }

        try {
            pr.setString(1, '%' + param + '%');
        } catch (SQLException e) {
            System.out.println("Problem with parameter");
            return null;
        }

        ResultSet rs = null;
        try {
            rs = pr.executeQuery();
        } catch (SQLException e) {

            System.out.println("Unable to execute query.");
            return null;
        }

        List<BookDTO> result = new LinkedList<>();
        if (!resultMethod(rs, result))
            return null;


        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Problem with closing querry");
            return null;
        }

        return result;
    }

    @Override
    public List<BookDTO> categoryBookWithRegularExpression(String param) {
        Connection connection = ConnectionFactory.getConnection();
        if (connection == null) {
            System.out.println("Unable to connect with DB");
            return null;
        }


        String sql = " SELECT * FROM book b  WHERE b.category LIKE ? ";

        PreparedStatement pr = null;
        try {
            pr = connection.prepareStatement(sql);
        } catch (SQLException e) {

            System.out.println("Unable to create statement");
            return null;
        }

        try {
            pr.setString(1, '%' + param + '%');
        } catch (SQLException e) {
            System.out.println("Problem with parameter");
            return null;
        }

        ResultSet rs = null;
        try {
            rs = pr.executeQuery();
        } catch (SQLException e) {

            System.out.println("Unable to execute query.");
            return null;
        }

        List<BookDTO> result = new LinkedList<>();
        if (!resultMethod(rs, result))
            return null;


        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Problem with closing querry");
            return null;
        }

        return result;
    }

    private boolean resultMethod(ResultSet rs, List<BookDTO> result) {
        try {
            while (rs.next()) {


                int idBook = rs.getInt(1);//tu masz id tej ksiazki jakbys potrzebowala
                String author = rs.getString(2);
                String category = rs.getString(3);
                int numberOfCopies = rs.getInt(4);
                String rentalTime = rs.getString(5);
                String title = rs.getString(6);

                BookDTO.rentalTime afterConversionRentalTime = null;
                if (rentalTime.equals("SEVENDAYS"))
                    afterConversionRentalTime = BookDTO.rentalTime.SEVENDAYS;
                if (rentalTime.equals("THREEMONTHS"))
                    afterConversionRentalTime = BookDTO.rentalTime.THREEMONTHS;
                else
                    afterConversionRentalTime = BookDTO.rentalTime.ONEDAY;

                BookDTO bookDTO = new BookDTO(title, author, category, afterConversionRentalTime, numberOfCopies);
                bookDTO.setIdBook(idBook);

                result.add(bookDTO);

            }
        } catch (SQLException e) {
            System.out.println("Problems with query results");
            return false;
        }
        return true;
    }
}
