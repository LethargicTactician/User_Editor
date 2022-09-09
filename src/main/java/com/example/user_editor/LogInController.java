package com.example.user_editor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class LogInController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField username_input;

    @FXML
    private TextField password_input;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(username_input.getText().trim().isEmpty() && password_input.getText().trim().isEmpty()){

                    System.out.println("Please fill in tall fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please fill in all fields");
                    alert.show();

                }else{
                    DBUtils.loginUser(actionEvent, username_input.getText(), password_input.getText());

                    ChangeScene changeScene = new ChangeScene();
                    try {
                        nameChange.username = username_input.getText();
                        changeScene.sceneChange(actionEvent,  "homeScreen.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });





    }
}
