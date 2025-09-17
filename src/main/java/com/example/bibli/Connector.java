package com.example.bibli;

import com.mysql.cj.jdbc.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connector {
    public static Connection ConDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/isp10","root","");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    // Метод для получения всех книг
    public static ObservableList<Book> getAllBooks(){
        Connection conn = ConDB();
        ObservableList<Book> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("year"),
                        rs.getString("description"),
                        rs.getInt("genre_id")
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке книг: " + e.getMessage());
        }
        return list;
    }

    // Метод для получения всех экземпляров книг
    public static ObservableList<BookCopy> getAllBookCopies(){
        Connection conn = ConDB();
        ObservableList<BookCopy> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM book_copies");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new BookCopy(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getString("inventory_number"),
                        rs.getString("barcode"),
                        rs.getString("status"),
                        rs.getString("location")
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке экземпляров книг: " + e.getMessage());
        }
        return list;
    }
}
