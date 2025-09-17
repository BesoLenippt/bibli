package com.example.bibli;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_login;

    @FXML
    private PasswordField id_pass;

    @FXML
    private Label id_regist;

    @FXML
    private Button id_vs;

    @FXML
    private Button id_vx;

    //переменные для работы с базой
    static ResultSet rss= null;
    Connection conn = null;
    PreparedStatement pst = null;

    //выход из программы
    @FXML
    void vs(ActionEvent event) {
        System.exit(0);
    }

    //вход в программу в зависимости от роли, и проверка на введенные соответствие в базе
    @FXML
    void vx(ActionEvent event) throws SQLException {
        try {
            conn = Connector.ConDB();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Не удалось подключиться к базе данных");
                return;
            }

            String sql = "SELECT * FROM users WHERE login = ? AND password_hash = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, id_login.getText());
            pst.setString(2, id_pass.getText());
            rss = pst.executeQuery();

            if (rss.next()) {
                String rol = rss.getString("role");

                // Закрываем текущее окно
                Stage currentStage = (Stage) id_vx.getScene().getWindow();

                if (rol.equals("librarian") || rol.equals("admin")) {
                    openLibrarianWindow(currentStage);
                } else if (rol.equals("reader")) {
                    openReaderWindow(currentStage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Дорогой(ай), данные введены неверно, попробуй еще раз");
                clearFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Ошибка при подключении к базе данных: " + e.getMessage());
        } finally {
            // Закрываем ресурсы
            try {
                if (rss != null) rss.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
    }

    // Метод для открытия окна библиотекаря
    private void openLibrarianWindow(Stage currentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MeniLidrar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 286, 163);
            Stage stage = new Stage();
            stage.setTitle("Библиотекарь - Библи");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не удалось открыть окно библиотекаря: " + e.getMessage());
        }
    }

    // Метод для открытия окна читателя
    private void openReaderWindow(Stage currentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MeniReder.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 286, 163);
            Stage stage = new Stage();
            stage.setTitle("Читатель - Библи");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не удалось открыть окно читателя: " + e.getMessage());
        }
    }

    @FXML
    void registration(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("avtoriz.fxml"));
        Stage stage1 = (Stage) id_regist.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 455, 236);
        Stage stage = new Stage();
        stage.setTitle("Библи регистрация");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    void initialize() {
        assert id_login != null : "fx:id=\"id_login\" was not injected: check your FXML file 'avtoriz.fxml'.";
        assert id_pass != null : "fx:id=\"id_pass\" was not injected: check your FXML file 'avtoriz.fxml'.";
        assert id_regist != null : "fx:id=\"id_regist\" was not injected: check your FXML file 'avtoriz.fxml'.";
        assert id_vs != null : "fx:id=\"id_vs\" was not injected: check your FXML file 'avtoriz.fxml'.";
        assert id_vx != null : "fx:id=\"id_vx\" was not injected: check your FXML file 'avtoriz.fxml'.";

        // Обработчик события наведения курсора
        id_regist.setOnMouseEntered(event -> {
            id_regist.setTextFill(Color.WHITE);
        });

        // Обработчик события ухода курсора
        id_regist.setOnMouseExited(event -> {
            id_regist.setTextFill(Color.PINK);
        });
    }

}
