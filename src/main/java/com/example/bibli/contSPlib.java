package com.example.bibli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class contSPlib {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book, String> avtor;

    @FXML
    private TableColumn<Book, String> descript;

    @FXML
    private TableColumn<Book, Integer> id;

    @FXML
    private TextField id_avtor;

    @FXML
    private TextField id_book;

    @FXML
    private TableColumn<BookCopy, Integer> id_book_raz;

    @FXML
    private Button id_del_book;

    @FXML
    private Button id_del_rasp;

    @FXML
    private TextField id_desc;

    @FXML
    private Button id_dob_book;

    @FXML
    private Button id_dob_rasp;

    @FXML
    private TextField id_izdat;

    @FXML
    private Button id_izmen_book;

    @FXML
    private Button id_izmen_rasp;

    @FXML
    private TextField id_name;

    @FXML
    private Button id_naz;

    @FXML
    private TextField id_poiskRasp;

    @FXML
    private TextField id_poiskbook;

    @FXML
    private TableColumn<BookCopy, Integer> id_rasp;

    @FXML
    private TextField id_status;

    @FXML
    private TextField id_stel;

    @FXML
    private Button id_vs;

    @FXML
    private TextField id_yers;

    @FXML
    private TableColumn<Book, Integer> isbn;

    @FXML
    private TableColumn<Book, String> iztatel;

    @FXML
    private TableColumn<Book, Integer> janer;

    @FXML
    private TableColumn<BookCopy, Integer> kod;

    @FXML
    private TableColumn<BookCopy, String> locatioon;

    @FXML
    private TableColumn<Book, String> name;

    @FXML
    private TableColumn<BookCopy, String> nomer;

    @FXML
    private TableColumn<BookCopy, String> status;

    @FXML
    private TableView<Book> table_book;

    @FXML
    private TableView<BookCopy> table_rasp;

    @FXML
    private TableColumn<Book, Integer> yers;

    @FXML
    void MouseClickBook(MouseEvent event) {

    }

    @FXML
    void MouseClickRasp(MouseEvent event) {

    }

    @FXML
    void delBook(ActionEvent event) {

    }

    @FXML
    void delRasp(ActionEvent event) {

    }

    @FXML
    void dobBook(ActionEvent event) {

    }

    @FXML
    void dobRasp(ActionEvent event) {

    }

    @FXML
    void izmenBook(ActionEvent event) {

    }

    @FXML
    void izmenRasp(ActionEvent event) {

    }

    @FXML
    void naz(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("Splib.fxml"));
        Stage stage1 = (Stage) id_naz.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MeniLidrar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 286, 163);
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
        assert avtor != null : "fx:id=\"avtor\" was not injected: check your FXML file 'Splib.fxml'.";
        assert descript != null : "fx:id=\"descript\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_avtor != null : "fx:id=\"id_avtor\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_book != null : "fx:id=\"id_book\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_book_raz != null : "fx:id=\"id_book_raz\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_del_book != null : "fx:id=\"id_del_book\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_del_rasp != null : "fx:id=\"id_del_rasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_desc != null : "fx:id=\"id_desc\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_dob_book != null : "fx:id=\"id_dob_book\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_dob_rasp != null : "fx:id=\"id_dob_rasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_izdat != null : "fx:id=\"id_izdat\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_izmen_book != null : "fx:id=\"id_izmen_book\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_izmen_rasp != null : "fx:id=\"id_izmen_rasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_name != null : "fx:id=\"id_name\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_naz != null : "fx:id=\"id_naz\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_poiskRasp != null : "fx:id=\"id_poiskRasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_poiskbook != null : "fx:id=\"id_poiskbook\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_rasp != null : "fx:id=\"id_rasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_status != null : "fx:id=\"id_status\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_stel != null : "fx:id=\"id_stel\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_vs != null : "fx:id=\"id_vs\" was not injected: check your FXML file 'Splib.fxml'.";
        assert id_yers != null : "fx:id=\"id_yers\" was not injected: check your FXML file 'Splib.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'Splib.fxml'.";
        assert iztatel != null : "fx:id=\"iztatel\" was not injected: check your FXML file 'Splib.fxml'.";
        assert janer != null : "fx:id=\"janer\" was not injected: check your FXML file 'Splib.fxml'.";
        assert kod != null : "fx:id=\"kod\" was not injected: check your FXML file 'Splib.fxml'.";
        assert locatioon != null : "fx:id=\"locatioon\" was not injected: check your FXML file 'Splib.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Splib.fxml'.";
        assert nomer != null : "fx:id=\"nomer\" was not injected: check your FXML file 'Splib.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'Splib.fxml'.";
        assert table_book != null : "fx:id=\"table_book\" was not injected: check your FXML file 'Splib.fxml'.";
        assert table_rasp != null : "fx:id=\"table_rasp\" was not injected: check your FXML file 'Splib.fxml'.";
        assert yers != null : "fx:id=\"yers\" was not injected: check your FXML file 'Splib.fxml'.";

    }

}
