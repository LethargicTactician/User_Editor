package com.example.user_editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label homeLabel;

    @FXML
    private Button btnLogout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            homeLabel.setText( "Welcome "+ nameChange.username + "!");
            btnLogout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
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