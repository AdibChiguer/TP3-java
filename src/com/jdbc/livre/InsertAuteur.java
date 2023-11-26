package com.jdbc.livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertAuteur {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/sys";
            String username = "root";
            String password = "12345";
            connection = DriverManager.getConnection(dbURL, username, password);

            System.out.println("Enter the code of the auteur");
            int code = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the prenom of the auteur");
            String prenom = scanner.nextLine();
            System.out.println("Enter the nom of the auteur");
            String nom = scanner.nextLine();
            System.out.println("Enter the tel of the auteur");
            String tel = scanner.nextLine();

            insertA(code, prenom, nom, tel);

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong", e);
        } 
    }

    private static void insertA(int code, String prenom, String nom, String tel) throws SQLException {

        String sql = "INSERT INTO auteur (code, prenom, nom, tel) VALUES (" + code + ", '" + prenom + "', '" + nom + "', '" + tel + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("Inserted successfully.");
        } else {
            System.out.println("Insertion failed.");
        }
    }
}
