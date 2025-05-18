package com.example.moviecatalog;

import database_layer.MovieEntity;
import database_layer.HibernateSetup;
import enums.Language;
import enums.Genre;
import enums.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Movie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieRegisterController {
    private static final Logger logger = LogManager.getLogger(MovieRegisterController.class);

    @FXML
    public TextField ratingField;

    @FXML
    public TextField titleField;

    private SessionFactory sessionFactory;


    @FXML
    private ComboBox<Genre> carCategoryComboBox;

    @FXML
    private ComboBox<Platform> carClassComboBox;

    @FXML
    private ComboBox<Language> carBrandComboBox;

    @FXML
    private Label resultText;

    @FXML
    public void initialize() {
        carCategoryComboBox.getItems().setAll(Genre.values());
        carClassComboBox.getItems().setAll(Platform.values());
        carBrandComboBox.getItems().setAll(Language.values());

        //carBrandComboBox.setEditable(true);
    }




    @FXML
    public void onRegisterButtonClick(ActionEvent actionEvent) {
        String title = titleField.getText();
        String ratingString = ratingField.getText();
        double rating = Double.parseDouble(ratingString);
        Genre selectedCategory = carCategoryComboBox.getValue();
        Platform selectedClass = carClassComboBox.getValue();
        Language selectedBrand = carBrandComboBox.getValue();


        Movie movie = new Movie(rating);
        MovieEntity movieE = new MovieEntity(rating);

        //System.out.println(movie);
        //logger.info(movie);
        //logger.info(movieE);

        resultText.setText("");

        Session session = HibernateSetup.getSessionFactory().openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            MovieEntity movieEntity = new MovieEntity(title, rating, selectedCategory, selectedClass, selectedBrand);
            session.persist(movieEntity);



            transaction.commit();
            //System.out.println("Movie saved successfully!");
            logger.info("Movie saved successfully!");
            resultText.setText("The movie is registered!");
            titleField.clear();
            ratingField.clear();
            carCategoryComboBox.getSelectionModel().clearSelection();
            carCategoryComboBox.setValue(null);
            carClassComboBox.getSelectionModel().clearSelection();
            carClassComboBox.setValue(null);
            carClassComboBox.getSelectionModel().clearSelection();
            carBrandComboBox.setValue(null);

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }




    }
}
