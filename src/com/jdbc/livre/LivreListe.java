package com.jdbc.livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LivreListe {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/sys";
            String username = "root";
            String password = "12345";
            connection = DriverManager.getConnection(dbURL, username, password);

            System.out.println("Enter the code of the author to get the list of books.");
            int code = Integer.parseInt(scanner.nextLine());
            selectListeOfBooks(code);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong", e);
        } finally {
            // Close resources in the finally block
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // handle or log the exception
            }

            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void selectListeOfBooks(int code) throws SQLException {
        String sql = "SELECT numero, titre, prix, edition FROM livre WHERE auteur_code = " + code;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("-- titre -- nbr -- prix -- edition");

        while (resultSet.next()) {
            int nbr = resultSet.getInt("numero");
            String titre = resultSet.getString("titre");
            int prix = resultSet.getInt("prix");
            int edition = resultSet.getInt("edition");

            System.out.println("--" + titre + "--" + nbr + "--" + prix + "--" + edition);
        }

        // Close resources
        resultSet.close();
        statement.close();
    }
}
