package com.example.bibli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menirederOt {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_bk;

    @FXML
    private Button id_naz;

    @FXML
    private Button id_sk;

    @FXML
    private Button id_vs;

    @FXML
    void bk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("MeniReder.fxml"));
        Stage stage1 = (Stage) id_naz.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Brread.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 381, 232);
        Stage stage = new Stage();
        stage.setTitle("Библи");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void naz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("MeniReder.fxml"));
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
    void sk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("MeniReder.fxml"));
        Stage stage1 = (Stage) id_naz.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Splread.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 381, 232);
        Stage stage = new Stage();
        stage.setTitle("Библи");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void vs(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert id_bk != null : "fx:id=\"id_bk\" was not injected: check your FXML file 'MeniReder.fxml'.";
        assert id_naz != null : "fx:id=\"id_naz\" was not injected: check your FXML file 'MeniReder.fxml'.";
        assert id_sk != null : "fx:id=\"id_sk\" was not injected: check your FXML file 'MeniReder.fxml'.";
        assert id_vs != null : "fx:id=\"id_vs\" was not injected: check your FXML file 'MeniReder.fxml'.";

    }

}
