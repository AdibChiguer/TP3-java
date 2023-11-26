package com.jdbc.livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SupprimerLivre {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/sys";
            String username = "root";
            String password = "12345";
            connection = DriverManager.getConnection(dbURL, username, password);

            System.out.println("Enter the code of the livre");
            int nmLivre = Integer.parseInt(scanner.nextLine());

            deleteL(nmLivre);

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

    private static void	deleteL(int nm) throws SQLException {

        String sql = "DELETE FROM livre WHERE numero = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, nm);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("Deletion failed.");
            }
        }
    }
}
