package com.company.project.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Klasa udostępniająca połączenia z bazą danych
public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            //Loading driver class
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to load driver");
            return null;
        }


        String URL = "jdbc:mysql://localhost:3306/myschema";

        Connection connection = null;

        try {
            //Creating connection object
            connection = DriverManager.getConnection(URL, "hr", "hr");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to create connection object.");
            return null;
        }

        return connection;

    }


}