package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private String url = "jdbc:postgresql://localhost:5432/telegramDatabase";
    private String dbUser = "postgres";
    private String dbPassword = "Rustam!2021";


    public void saveClient(String b, Double totalsum, String name) {
        String b1 = b;
        Double total = totalsum;
        String name1 = name;

        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "insert into  telebot (column1, column2, column3) values (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, b1);
            pstmt.setDouble(2, total);
            pstmt.setString(3, name1);
            pstmt.execute();
            System.out.println("user added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
