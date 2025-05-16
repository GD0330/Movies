package com.example.moviecatalog.administrator;

import com.example.moviecatalog.Application;
import database_layer.AdministratorEntity;
import database_layer.HibernateSetup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.CurrentUser;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;

public class AdministratorLoginController {
    @FXML
    public TextField usernameField;

//    @FXML
//    public TextField passwordField1;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageText;

    @FXML
    public void onLoginButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();



        Session session = HibernateSetup.getSessionFactory().openSession();
        try {
            StringBuilder sql = new StringBuilder("FROM AdministratorEntity WHERE username = :username AND password = :password ");
            Query<AdministratorEntity> query1 = session.createQuery(sql.toString(), AdministratorEntity.class);
            query1.setParameter("username", username);
            query1.setParameter("password", password);

            AdministratorEntity admin = query1.uniqueResult();

            if(admin != null){
                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.close();

                CurrentUser.setName(username);
                CurrentUser.setUser(admin);

                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view-administrator.fxml"));

                Stage stage = new Stage();
                //Scene scene = new Scene(fxmlLoader.load(), 250, 370);
                Scene scene = new Scene(fxmlLoader.load(), 620, 440);
                //Scene scene = new Scene(fxmlLoader.load());
                //stage.setTitle("System");
                stage.setTitle("System" + " (" + CurrentUser.getName() + ")");
                stage.setScene(scene);
                stage.show();


            }else {
                System.out.println("Invalid username or password");
                messageText.setText("Invalid username or password");
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }



//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view-administrator.fxml"));
//
//        Stage stage = new Stage();
//        //Scene scene = new Scene(fxmlLoader.load(), 250, 370);
//        Scene scene = new Scene(fxmlLoader.load(), 620, 440);
//        //Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("System");
//        stage.setScene(scene);
//        stage.show();
    }
}
