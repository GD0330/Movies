package com.example.moviecatalog;

import database_layer.AdministratorEntity;
import database_layer.HibernateSetup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRegisterController {
    private static final Logger logger = LogManager.getLogger(UserRegisterController.class);
    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    public void onRegisterButtonClick(ActionEvent actionEvent) {
        String name = nameField.getText();
        String password = passwordField.getText();

        Session session = HibernateSetup.getSessionFactory().openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();


            //OperatorEntity operatorEntity = new OperatorEntity(name, phoneNumber);
            AdministratorEntity administratorEntity = new AdministratorEntity(name, password);

            session.persist(administratorEntity);


            transaction.commit();
            //System.out.println("Operator saved successfully!");
            //logger.info("User saved successfully!");
            //resultText.setText("The user is registered!");
            nameField.clear();
            passwordField.clear();


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
