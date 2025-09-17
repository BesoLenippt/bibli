module com.example.bibli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.bibli to javafx.fxml;
    exports com.example.bibli;
}