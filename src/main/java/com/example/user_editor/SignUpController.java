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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    //this is connected to newAccForm.fxml

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private TextField username_;

    @FXML
    private TextField password_;

    @FXML
    private Button loginbtn;

    @FXML
    private Button createbtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(username_.getText().trim().isEmpty() && password_.getText().trim().isEmpty()){

                    System.out.println("Please fill in all fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please fill in all fields");
                    alert.show();

                }else{
                    DBUtils.signupUser(actionEvent, firstname.getText(), lastname.getText(), email.getText(), username_.getText(), password_.getText());
                }
            }
        });

        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //DBUtils.changeScene(actionEvent, "loginDocument.fxml", "Log in!", null);
                ChangeScene changeScene = new ChangeScene();
                try {
                    changeScene.sceneChange(actionEvent,  "loginDocument.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
