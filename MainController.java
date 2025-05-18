package com.example.moviecatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.CurrentUser;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("movie_register.fxml"));

        Stage stage = new Stage();
        //Scene scene = new Scene(fxmlLoader.load(), 250, 370);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Movie registration");
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    protected void onInformationButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("movies.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Movies");
        stage.setScene(scene);
        stage.show();
    }


}