package com.example.bibli;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_fam;

    @FXML
    private TextField id_im;

    @FXML
    private TextField id_login;

    @FXML
    private Button id_naz;

    @FXML
    private PasswordField id_pass;

    @FXML
    private TextField id_phone;

    @FXML
    private Button id_reg;

    @FXML
    private Button id_vs;

    Connection conn = null;
    PreparedStatement pst = null;

    @FXML
    void naz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Stage stage1 = (Stage) id_naz.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("avtoriz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 381, 232);
        Stage stage = new Stage();
        stage.setTitle("Библи");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void reg(ActionEvent event) {
        // Переменные для взятия данных из текстовых полей
        String im = id_im.getText().trim();
        String fam = id_fam.getText().trim();
        String email = id_email.getText().trim();
        String phone = id_phone.getText().trim();
        String login = id_login.getText().trim();
        String password = id_pass.getText().trim();

        // Валидация полей
        if(im.isEmpty() || fam.isEmpty() || email.isEmpty() ||
                login.isEmpty() || password.isEmpty()){
            showAlert("Ошибка", "Дорогой(ай), пожалуйста, заполните все поля");
            return;
        }
        // Проверка уникальности логина и email
        if (!isLoginUnique(login)) {
            showAlert("Ошибка", "Дорогой(ай), этот логин уже занят");
            return;
        }

        if (!isEmailUnique(email)) {
            showAlert("Ошибка", "Дорогой(ай), этот email уже зарегистрирован");
            return;
        }

        // Генерация уникального номера читательского билета
        String cardNumber = generateUniqueCardNumber();
        if (cardNumber == null) {
            showAlert("Ошибка", "Дорогой(ай), не удалось сгенерировать номер читательского билета");
            return;
        }

        try (Connection connection = Connector.ConDB()) {
            // Вставляем данные в таблицу patrons
            String insertPatronSQL = "INSERT INTO patrons (first_name, last_name, email, phone, card_number, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertPatronSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, im);
                pstmt.setString(2, fam);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.setString(5, cardNumber);
                pstmt.setDate(6, Date.valueOf(LocalDate.now()));

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    // Получаем ID вставленного читателя
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int patronId = generatedKeys.getInt(1);

                            // Вставляем данные в таблицу users
                            String insertUserSQL = "INSERT INTO users (patron_id, login, password_hash, role) " +
                                    "VALUES (?, ?, ?, ?)";

                            try (PreparedStatement userStmt = connection.prepareStatement(insertUserSQL)) {
                                userStmt.setInt(1, patronId);
                                userStmt.setString(2, login);
                                userStmt.setString(3, password);
                                userStmt.setString(4, "reader");

                                int userAffectedRows = userStmt.executeUpdate();

                                if (userAffectedRows > 0) {
                                    showAlert("Успех", "Дорогой(ай), Регистрация прошла успешно! Ваш номер читательского билета: " + cardNumber);

                                    // Очищаем поля после успешной регистрации
                                    id_im.clear();
                                    id_fam.clear();
                                    id_email.clear();
                                    id_phone.clear();
                                    id_login.clear();
                                    id_pass.clear();
                                } else {
                                    showAlert("Ошибка", "Дорогой(ай), не удалось создать учетную запись");
                                }
                            }
                        } else {
                            showAlert("Ошибка", "Дорогой(ай), не удалось получить ID читателя");
                        }
                    }
                } else {
                    showAlert("Ошибка", "Дорогой(ай), не удалось зарегистрировать читателя");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Ошибка", "Дорогой(ай), ошибка базы данных: " + e.getMessage());
        }
    }

    // Проверка уникальности логина
    private boolean isLoginUnique(String login) {
        try (Connection connection = Connector.ConDB();
             PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM users WHERE login = ?")) {

            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery()) {
                return !rs.next(); // Если нет результатов - логин уникален
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Проверка уникальности email
    private boolean isEmailUnique(String email) {
        try (Connection connection = Connector.ConDB();
             PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM patrons WHERE email = ?")) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return !rs.next(); // Если нет результатов - email уникален
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Генерация уникального номера читательского билета
    private String generateUniqueCardNumber() {
        Random random = new Random();
        int attempts = 0;
        final int MAX_ATTEMPTS = 100;

        while (attempts < MAX_ATTEMPTS) {
            int number = random.nextInt(90000) + 10000; // Генерируем 5-значное число
            String cardNumber = "LIB-" + number;

            if (isCardNumberUnique(cardNumber)) {
                return cardNumber;
            }

            attempts++;
        }

        return null;
    }

    // Проверка уникальности номера читательского билета
    private boolean isCardNumberUnique(String cardNumber) {
        try (Connection connection = Connector.ConDB();
             PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM patrons WHERE card_number = ?")) {

            pstmt.setString(1, cardNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                return !rs.next(); // Если нет результатов - номер уникален
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Вспомогательный метод для показа уведомлений
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void vs(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert id_email != null : "fx:id=\"id_email\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_fam != null : "fx:id=\"id_fam\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_im != null : "fx:id=\"id_im\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_login != null : "fx:id=\"id_login\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_naz != null : "fx:id=\"id_naz\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_pass != null : "fx:id=\"id_pass\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_phone != null : "fx:id=\"id_phone\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_reg != null : "fx:id=\"id_reg\" was not injected: check your FXML file 'registration.fxml'.";
        assert id_vs != null : "fx:id=\"id_vs\" was not injected: check your FXML file 'registration.fxml'.";

    }

}
