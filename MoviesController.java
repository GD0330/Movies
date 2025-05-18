package com.example.moviecatalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CurrentUser;

import java.io.IOException;

public class MoviesController {
//    private ObservableList<String> notifications = FXCollections.observableArrayList();
//    @FXML
//    private ListView<String> notificationListView;
//
//    @FXML
//    public void initialize() {
//        notificationListView.setItems(notifications);
//    }

    @FXML
    public void onAvailableCarsButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("watchlist.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Watchlist");
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void onStatisticsButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("explore.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Explore");
        stage.setScene(scene);
        stage.show();
    }


}

