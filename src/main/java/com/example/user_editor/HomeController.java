package com.example.user_editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private Label homeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                ChangeScene changeScene = new ChangeScene();
                try {

                    changeScene.sceneChange(actionEvent,  "loginDocument.fxml");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //   DBUtils.changeScene(actionEvent, "newAccForm.fxml", "Log in!", null);

            }
        });

    }

    public void setUserInfo(String username){
        homeLabel.setText("Welcome " + username + "!");
    }
}
