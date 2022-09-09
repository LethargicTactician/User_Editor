package com.example.user_editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    static String url = "jdbc:mysql://localhost:3306/usereditor?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String _password = "Colacion#324265";


    //when a user creates another account
    public static void signupUser(ActionEvent event, String firstName, String lastName, String email, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet results = null;


        try {
            connection = DriverManager.getConnection(url, user, _password);
            psCheckUserExists = connection.prepareStatement("SELECT * from person where username = ?");
            psCheckUserExists.setString(1, username);
            results = psCheckUserExists.executeQuery();

            //if user already exists
            if (results.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();

            } else { // add it in the dbt
                psInsert = connection.prepareStatement("INSERT INTO person(firstName, lastName, email, username, password) VALUES (?, ?, ?, ?, ?)");
                psInsert.setString(1, firstName);
                psInsert.setString(2, lastName);
                psInsert.setString(3, email);
                psInsert.setString(4, username);
                psInsert.setString(5, password);
                //return results in dbt
                psInsert.executeUpdate();

                ChangeScene changeScene = new ChangeScene();
                changeScene.sceneChange(event,  "homeScreen.fxml");

                //changeScene(event, "homeScreen.fxml", "Welcome ", username + "!");


            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //user is trying to log in
    public static void loginUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection(url, user, _password);
            preparedStatement = connection.prepareStatement("SELECT password FROM person WHERE username = ?");

            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery();

            if(result.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("provided credentials are incorrect");
//                alert.show();
            } else{
                while(result.next()){
                    String retreivePassword = result.getString("password");
                    if(retreivePassword.equals(password)){
                        ChangeScene changeScene = new ChangeScene();
                        changeScene.sceneChange(event,  "homeScreen.fxml");
//                        changeScene(event "Welcome ", username + "!");

                    } else{
                        System.out.println("Password is incorrect");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {

            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }


}
