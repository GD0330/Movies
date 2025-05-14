package com.example.moviecatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CurrentUser;

import java.io.IOException;

public class LoginController {
    @FXML
    protected void onAdministratorButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login_administrator.fxml"));

        Stage stage = new Stage();
        //Scene scene = new Scene(fxmlLoader.load(), 250, 370);
        //Scene scene = new Scene(fxmlLoader.load(), 620, 440);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void onRegisterButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("user_register.fxml"));

        Stage stage = new Stage();
        //Scene scene = new Scene(fxmlLoader.load(), 350, 370);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

    }
}
