module com.example.user_editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.user_editor to javafx.fxml;
    exports com.example.user_editor;
}